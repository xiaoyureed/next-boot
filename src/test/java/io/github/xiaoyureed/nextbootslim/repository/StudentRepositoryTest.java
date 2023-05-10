package io.github.xiaoyureed.nextbootslim.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.xiaoyureed.nextbootslim.core.JpaRepositoryTest;
import io.github.xiaoyureed.nextbootslim.domain.common.enums.GenderEnum;
import io.github.xiaoyureed.nextbootslim.domain.entity.Student;
import io.github.xiaoyureed.nextbootslim.domain.repository.StudentRepositoryI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@JpaRepositoryTest
//@Sql()
public class StudentRepositoryTest {

    @Autowired
    StudentRepositoryI studentRepository;

    @Test
    void should_be_ok_when_save_one_single_student() {
        Student student = new Student("testest", 1, GenderEnum.MALE);
        Student saved = studentRepository.save(student);
        Optional<Student> byId = studentRepository.findById(saved.getId());
        assertThat(student.getId(), is(not(emptyString())));
//        assertThat(byId.isPresent(), equalTo(true));
//        System.out.println(byId.get());
//
//        assertThat(byId.get().getId(), equalTo(saved.getId()));
//        assertThat(byId.get().getCreatedAt(), is(not(null)));
    }
}
