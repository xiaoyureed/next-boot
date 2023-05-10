package io.github.xiaoyureed.nextbootslim;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.github.xiaoyureed.nextbootslim.core.DatabaseTestConfiguration;

//@SpringBootTest
//@AutoConfigureMockMvc
//@Import(DatabaseTestConfiguration.class)
public class SmokeTest {
    @Autowired
    MockMvc mockMvc;

//    @Test
    void should_return_ok_when_request_health_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"));
    }
}
