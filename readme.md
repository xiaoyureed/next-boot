
- [create the basic structure](#create-the-basic-structure)
- [perform the integration process manually](#perform-the-integration-process-manually)
- [integrate with maven plugins](#integrate-with-maven-plugins)
- [How to develop and debug](#how-to-develop-and-debug)
- [reference materials](#reference-materials)


## create the basic structure

First of all, we have to create a spring boot app and a react app, As regards of spring boot app, please refer to https://start.spring.io. Then configurations about the static content route need set up:

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * To serve the nextjs static resources directly
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

/**
     * 为静态页面的路由自动补全 .html 然后重定向 (nextjs在打包进 springboot 后, 作为springboot 里的静态资源, 整个应用只认识 xxx.html. 当然如果前后分离开发时, nextjs 的服务器是认识 xxx的)
     * 
     * 开发阶段可以没有, 但是打包时要加上
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                boolean isApiHandle = handler instanceof HandlerMethod;
                String servletPath = request.getServletPath();

                if (!isApiHandle && !"/".contentEquals(servletPath) && FilenameUtils.getExtension(servletPath).isEmpty()) {
                    request.getRequestDispatcher(servletPath + ".html").forward(request, response);
                    return false;
                }

                return true;
            }
        });
    }

```

For next.js app, check the steps below: (Execute the following command in the root path of the java project)

> Usually I name the next.js app with "frontend"

```shell
npx create-next-app@latest --ts
# or
yarn create next-app --ts
# or
pnpm create next-app --typescript

```

change the next.config.js

```js
/** @type {import('next').NextConfig} */
const nextConfig = {
  //https://stackoverflow.com/questions/65058598/nextjs-cors-issue
  async rewrites() {
    return {
      fallback: [
        {
          source: '/api/:path*',
          destination: 'http://localhost:8080/api/:path*'
        }
      ]
    }
  },
  reactStrictMode: true,
  images: {
    unoptimized: true
  },
  swcMinify: true
}

module.exports = nextConfig

```

change the package.json (Just insert the following line into "scripts" section)

```json
"export": "next build && next export"
```



## perform the integration process manually

```
Step -1 : Edit package.json -> add "export":"next export" in the scripts property

    This measure is just form normal react app, if come up with next.js app, please refer to above

Step - 2: The above step will build a folder named "out". Copy the contents of out folder. Paste it in the src -> main -> resources -> static folder.

Step -3: Now build the spring boot jar/war, then run it. It will serve the contents inside the static folder.
```

## integrate with maven plugins

By using plugin `frontend-maven-plugin` and `maven-resources-plugin`

```xml
<!-- just for using npm -->
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>1.11.3</version>
    <executions>
      <!-- check if nodejs/npm installed -->
        <execution>
            <id>install node and npm</id>
            <goals>
                <goal>install-node-and-npm</goal>
            </goals>
            <!-- optional, the default value is like this -->
            <phase>generate-resources</phase>
        </execution>
        <!-- clear node_modules and package-lock.json -->
        <execution>
            <id>npm run clean</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
                <arguments>run clean</arguments>
            </configuration>
        </execution>
        <!-- install dependencies -->
        <execution>
            <id>npm install</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
                <arguments>install --registry=https://registry.npm.taobao.org</arguments>
            </configuration>
        </execution>
        <execution>
            <id>npm run build and export</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
                <arguments>run export</arguments>
            </configuration>
        </execution>
    </executions>
    <configuration>
        <workingDirectory>${project.baseDir}/frontend</workingDirectory>
        <installDirectory>${project.build.directory}</installDirectory>
        <nodeVersion>v18.14.0</nodeVersion>
        <!-- optional, just for projects in china main land -->
        <!-- <downloadRoot>http://npm.taobao.org/mirrors/node/</downloadRoot> -->
    </configuration>
</plugin>

<!-- just for using yarn -->
<plugin>
                <groupId>com.github.eirslett</groupId>
                <artifactId>frontend-maven-plugin</artifactId>
                <version>1.11.3</version>
                <executions>
                    <!-- check if nodejs/npm installed -->
                    <execution>
                        <id>install-frontend-tools</id>
                        <goals>
                            <goal>install-node-and-yarn</goal>
                        </goals>
                        <!-- optional, the default value is like this -->
                        <phase>generate-resources</phase>
                    </execution>

                    <!-- install dependencies -->
                    <execution>
                        <id>yarn-install</id>
                        <goals>
                            <goal>yarn</goal>
                        </goals>
                        <phase>generate-resources</phase>
                        <configuration>
                            <arguments>install --registry=https://registry.npm.taobao.org</arguments>
<!--                            <arguments>install</arguments>-->
                        </configuration>
                    </execution>
                    <execution>
                        <id>yarn-build-and-export</id>
                        <goals>
                            <goal>yarn</goal>
                        </goals>
                        <phase>generate-resources</phase>
                        <configuration>
                            <arguments>export</arguments>
                        </configuration>
                    </execution>
                </executions>
                <configuration>
                    <workingDirectory>${frontend.dir}</workingDirectory>
                    <installDirectory>${project.build.directory}</installDirectory>
                    <nodeVersion>v18.14.0</nodeVersion>
                    <yarnVersion>v1.22.19</yarnVersion>
                    <!-- optional, just for projects in China main land -->
                    <!-- <downloadRoot>http://npm.taobao.org/mirrors/node/</downloadRoot> -->
                </configuration>
            </plugin>

<plugin>
				<artifactId>maven-resources-plugin</artifactId>
				<version>3.2.0</version>
				<executions>
					<execution>
						<id>copy-resources</id>
						<phase>generate-resources</phase>
						<goals>
							<goal>copy-resources</goal>
						</goals>
						<configuration>
							<outputDirectory>${basedir}/target/classes/static</outputDirectory>
              <!-- or -->
              <!-- <outputDirectory>${project.build.outputDirectory}/static</outputDirectory> -->
							<resources>
								<resource>
									<directory>${basedir}/frontend/out</directory>
									<filtering>false</filtering>
								</resource>
							</resources>
						</configuration>
					</execution>
				</executions>
			</plugin>
```

Then we can issue command `mvn spring-boot:run` and visit http://localhost:8080 to check the effect

Using `mvn package -D maven.test.skip=true` to package jar file.

## How to develop and debug

So far, we can only package the final jar file with the above setup, but how to develop and debug with enjoying the benefits of hot reload?

We should follow the traditional way of separating the backend and frontend to conduct the development.

For example, now we have a backend service:

```java

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<BaseResponse<List<Student>>> findAll() throws Exception {
        return ResponseEntity.ok(BaseResponse.ok(studentRepository.findAll()));
    }
}

```

and a nextjs page:

```ts
import {useEffect, useState} from "react";

interface Student {
    id: string;
    name: string;
    age: number;
    gender: number;
    createdAt: Date;
    updatedAt: Date;
}

const Students = () => {

    let [students, setStudents] = useState<Student[] | null>(null);
    let [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true)

        fetch('/api/students', {
            method: 'POST'
        })
            .then(resp => resp.json())
            .then(resp => {
                setStudents(() => resp.data)
                setLoading(false)
            })
    }, [])

    return (
        <main>
            <ul>
                {loading ? (<div>loading</div>) : students?.map((student, i) => {
                    return (
                        <li key={i}>
                            {`${student.id} | ${student.name} | ${student.age} | ${student.gender === 1 ? '男' : '女'} | ${student.createdAt} | ${student.updatedAt}`}
                        </li>
                    )
                })}
            </ul>
        </main>
    )
}

export default Students

```

Then we can start the backend with the debug/start button offered by ide or by using the main method directly, the backend service will start on port 8080, for the frontend app, enter `frontend` folder, issue `yarn dev` to start the frontend dev server on port 3000, now we can visit localhost:3000 to check the page
