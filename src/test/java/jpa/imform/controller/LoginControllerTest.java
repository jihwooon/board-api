package jpa.imform.controller;

import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@DisplayName("SessionController")
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationServiceImpl authenticationServiceImpl;

  @BeforeEach
  void setUp() {

    given(authenticationServiceImpl.login("rfrid1b@squidoo.com", "8ixYbxleTQ")).willReturn("a.b.c");
  }
  @Test
  void login() throws Exception {
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"rfrid1b@squidoo.com\", \"password\" : \"8ixYbxleTQ\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString(".")));
  }
}
