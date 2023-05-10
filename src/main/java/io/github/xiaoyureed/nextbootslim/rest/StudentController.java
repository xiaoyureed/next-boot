package io.github.xiaoyureed.nextbootslim.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.xiaoyureed.nextbootslim.core.RequestWrapper;
import io.github.xiaoyureed.nextbootslim.core.ResponseWrapper;
import io.github.xiaoyureed.nextbootslim.domain.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create-student")
    public ResponseEntity<ResponseWrapper<?>> createStudent(@RequestBody RequestWrapper req) throws Exception {
        return ResponseEntity.ok(ResponseWrapper.ok(studentService.createStudent(req)));
    }

}
