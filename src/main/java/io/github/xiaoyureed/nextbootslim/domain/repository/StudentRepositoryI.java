package io.github.xiaoyureed.nextbootslim.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.github.xiaoyureed.nextbootslim.core.jpa.BaseRepositoryI;
import io.github.xiaoyureed.nextbootslim.domain.entity.Student;
import io.github.xiaoyureed.nextbootslim.domain.projection.StudentBasicInfo;

public interface StudentRepositoryI extends BaseRepositoryI<Student, String> {

    List<Student> findByName(String name);

    @Query(value = "select id from student where student.name = ?1", nativeQuery = true)
    @Transactional(readOnly = true)
    List<String> findAllIds(String name);

    @Transactional(readOnly = true)
    @Query(value = "select age, name, gender from student", nativeQuery = true)
    List<StudentBasicInfo> findprojection();

    @Transactional(timeout = 5)
    @Modifying
    @Query(value = "update student set age = 111 where name = :name", nativeQuery = true)
    void updatedemo(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "delete from student where name = :name", nativeQuery = true)
    void deletedemo(@Param("name") String name);

    default List<Student> querySpecdemo(String name, int age) {
        return null;
    }

}
