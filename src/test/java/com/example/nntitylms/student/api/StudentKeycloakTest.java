package com.example.nntitylms.Student.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StudentKeycloakTest {

//    @MockBean
//    private JwtDecoder jwtDecoder;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(authorities = "VIEW_CODELABS")
//    void name() throws Exception {
//        mockMvc.perform(get("/students")).andExpect(status().isOk());
//    }
}