package com.example.nntitylms.course.api;

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
public class CourseKeycloakTest {

    @MockBean
    private JwtDecoder jwtDecoder;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "VIEW_COURSES_OVERVIEW")
    void SeeCourseProgressAsUserWithViewCoursesOverviewAuthorization() throws Exception {
        mockMvc.perform(get("/courses/2812b4ba-90ea-497d-9185-16772cc475f6")).andExpect(status().isOk());
    }

    @Test
    void SeeCourseProgressAsUserWithoutAuthorization() throws Exception {
        mockMvc.perform(get("/courses/2812b4ba-90ea-497d-9185-16772cc475f6")).andExpect(status().isUnauthorized());
    }
}
