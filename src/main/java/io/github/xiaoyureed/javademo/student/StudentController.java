package io.github.xiaoyureed.javademo.student;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.xiaoyureed.javademo.base.Response;

@RestController
@RequestMapping("/api/students")
public class StudentController {
   
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Response<List<Student>>> findAll() throws Exception {
        return ResponseEntity.ok(Response.ok(studentService.findAll()));
    }

}
