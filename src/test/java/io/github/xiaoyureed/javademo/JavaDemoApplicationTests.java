package io.github.xiaoyureed.javademo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.xiaoyureed.javademo.base.BaseService;
import io.github.xiaoyureed.javademo.student.Student;

@SpringBootTest
class JavaDemoApplicationTests {

    @Autowired
    BaseService<Student, String> studentService;

    @Test
    void contextLoads() {
        List<Student> all = studentService.findAll();
        System.out.println(all);
    }

}
