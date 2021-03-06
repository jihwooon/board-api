package jpa.imform.controller;

import jpa.imform.dto.SessionDto;
import jpa.imform.service.impl.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

  private final AuthenticationService authenticationServiceImpl;

  public SessionController(AuthenticationService authenticationServiceImpl) {
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SessionDto.SessionResponse login() {
    String accessToken = authenticationServiceImpl.login();

    return SessionDto.SessionResponse.builder()
        .accessToken(accessToken)
        .build();
  }

}
