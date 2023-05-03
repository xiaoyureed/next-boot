package io.github.xiaoyureed.javademo.student;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.xiaoyureed.javademo.base.BaseResponse;

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
