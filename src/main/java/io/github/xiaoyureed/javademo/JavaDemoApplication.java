package io.github.xiaoyureed.javademo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.github.xiaoyureed.javademo.base.BaseService;
import io.github.xiaoyureed.javademo.student.Student;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class JavaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner initDB(BaseService<Student, String> repo) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                repo.save(new Student("xiaoyu", 11, 2));
                repo.save(new Student("jojo", 150, 1));
            }
        };
    }
}
