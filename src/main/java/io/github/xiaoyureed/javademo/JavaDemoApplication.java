package io.github.xiaoyureed.javademo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.xiaoyureed.javademo.student.Student;
import io.github.xiaoyureed.javademo.student.StudentRepositoryI;

@SpringBootApplication
public class JavaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner initDB(StudentRepositoryI repo) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                repo.save(new Student("xiaoyu", 11, 2));
                repo.save(new Student("jojo", 150, 1));
            }
        };
    }
}
