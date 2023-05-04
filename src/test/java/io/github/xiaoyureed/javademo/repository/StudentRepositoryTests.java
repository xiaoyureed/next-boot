package io.github.xiaoyureed.javademo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import io.github.xiaoyureed.javademo.student.Student;
import io.github.xiaoyureed.javademo.student.StudentRepositoryI;
import io.github.xiaoyureed.javademo.student.view.StudentBasicInfo;

@SpringBootTest
public class StudentRepositoryTests {
    @Autowired
    StudentRepositoryI studentRepository;

    @Test
    void testNativeQuery() {
        List<String> ids = studentRepository.findAllIds("xiaoyu");
        System.out.println(String.join("-", ids));

        List<StudentBasicInfo> all = studentRepository.findprojection();
        System.out.println(all);

        studentRepository.updatedemo("xiaoyu");
        List<Student> findAll = studentRepository.findAll();
        System.out.println(findAll);

        List<Student> xiaoyuContainer = studentRepository.findByName("xiaoyu");
        System.out.println(xiaoyuContainer);
        studentRepository.deletedemo("xiaoyu");
        studentRepository.findOne(Example.of(Student.builder().name("xiaoyu").build())).ifPresent(s -> {
            System.out.println("exit? " + s);
        });

    }


}
