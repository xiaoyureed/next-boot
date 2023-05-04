package io.github.xiaoyureed.javademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.xiaoyureed.javademo.base.ServiceI;
import io.github.xiaoyureed.javademo.student.Student;

@SpringBootTest
class JavaDemoApplicationTests {

    @Autowired
    ServiceI<Student, String> studentService;

    @Test
    void contextLoads() {
    }

}
