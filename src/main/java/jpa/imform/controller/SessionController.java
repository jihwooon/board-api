//TODO : JWT을 이용한 로그인 기능 구현
// SessionController
// AuthenticationService
// AuthenticationInterceptor
// JWTUtil
// SessionRequest, SessionResponses DTO

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

  private final AuthenticationService authenticationService;

  public SessionController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SessionDto.SessionResponse login() {
    String accessToken = authenticationService.login();

    return SessionDto.SessionResponse.builder()
        .accessToken(accessToken)
        .build();
  }

}
