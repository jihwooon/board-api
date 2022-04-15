package jpa.imform.controller;


import jpa.imform.dto.LoginDto;
import jpa.imform.service.impl.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@DisplayName("LoginController")
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationService authenticationService;


  @BeforeEach
  void setUp() {

    LoginDto.LoginInRequest request = LoginDto.LoginInRequest.builder()
        .email("jihwooon@example.com")
        .password("1234")
        .build();

//    given(authenticationService.login(request)).willReturn("a.b.c");
//    given(authenticationService.login(request)).willThrow(new InvalidTokenException("Not have token"));
  }

  @Test
  void login() throws Exception {
    mockMvc.perform(post("/login"))
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString(".")));
  }

  @Test
  void loginWithNotToken() throws Exception {
    mockMvc.perform(post("/login"))
        .andExpect(status().isUnauthorized());
  }

}
