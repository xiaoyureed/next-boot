package io.github.xiaoyureed.nextbootslim.rest;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.xiaoyureed.nextbootslim.domain.service.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void should_return_ok_when_create_student() throws Exception {
        String id = "1";
        BDDMockito.given(studentService.createStudent(Mockito.any())).willReturn(Collections.singletonMap("id", id));

        byte[] content = new ClassPathResource("request/student/create/single_one.json").getInputStream().readAllBytes();

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/students/create-student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.id").value(id));
    }
}
