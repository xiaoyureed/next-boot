package io.github.xiaoyureed.nextbootslim.domain.service;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import io.github.xiaoyureed.nextbootslim.core.RequestWrapper;
import io.github.xiaoyureed.nextbootslim.core.jpa.BaseRepositoryI;
import io.github.xiaoyureed.nextbootslim.core.jpa.BaseServiceI;
import io.github.xiaoyureed.nextbootslim.core.jpa.NativeSqlRepository;
import io.github.xiaoyureed.nextbootslim.core.util.BeanUtils;
import io.github.xiaoyureed.nextbootslim.domain.common.enums.GenderEnum;
import io.github.xiaoyureed.nextbootslim.domain.entity.Student;
import io.github.xiaoyureed.nextbootslim.domain.repository.StudentRepositoryI;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@Service
@Transactional
@Slf4j
public class StudentService implements BaseServiceI<Student, String> {

    private final StudentRepositoryI studentRepository;

    private final NativeSqlRepository nativeSqlRepository;

    public StudentService(StudentRepositoryI studentRepository, NativeSqlRepository nativeSqlRepository) {
        this.studentRepository = studentRepository;
        this.nativeSqlRepository = nativeSqlRepository;
    }

    public Map<String, Object> createStudent(RequestWrapper req) {
        return BeanUtils.toMap(studentRepository.save(req.to(Student.class)));
    }

    @Override
    public BaseRepositoryI<Student, String> getRepository() {
        return this.studentRepository;
    }

    public void insertSomeStudents() {
        Faker faker = new Faker(Locale.SIMPLIFIED_CHINESE);

        int count = 25;
        ArrayList<Student> students = new ArrayList<Student>(count);
        for (int i = 0; i < count; i++) {
            students.add(Student.builder()
                .name(faker.name().fullName())
                .age(faker.number().randomDigit())
                .gender(GenderEnum.of(faker.random().nextInt(0, 1)))
                .build()
            );
        }
        studentRepository.saveAllAndFlush(students);
        log.info("\nsuccessfully insert [{}] students into database\n", count);

        String sql = "insert student (id, name, age, gender) values ('111', 'xiaoyu', 29, 0), ('222', 'jojo', 11, 0), ('333', 'Jerry', 44, 1)";
        Integer rows = nativeSqlRepository.update(sql);
        log.info("\nsuccessfully insert [{}] students into database\n", rows);
    }

}
