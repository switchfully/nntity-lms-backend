package com.example.nntitylms.user.api;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
class UserKeycloakTest {

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