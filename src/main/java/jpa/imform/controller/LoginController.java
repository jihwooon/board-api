package jpa.imform.controller;

import jpa.imform.dto.LoginDto;
import jpa.imform.service.impl.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  private final AuthenticationService authenticationServiceImpl;

  public LoginController(AuthenticationService authenticationServiceImpl) {
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginDto.LoginInResponse login(final LoginDto.LoginInRequest request) {
    String accessToken = authenticationServiceImpl.login(request);

    return LoginDto.LoginInResponse.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .accessToken(accessToken)
        .build();
  }

}
