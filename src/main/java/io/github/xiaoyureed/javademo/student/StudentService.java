package io.github.xiaoyureed.javademo.student;

import org.springframework.stereotype.Service;

import io.github.xiaoyureed.javademo.base.RepositoryI;
import io.github.xiaoyureed.javademo.base.ServiceI;

@Service
public class StudentService implements ServiceI<Student, String> {

    private StudentRepositoryI studentRepository;

    public StudentService(StudentRepositoryI studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public RepositoryI<Student, String> getRepository() {
        return this.studentRepository;
    }
    
}
