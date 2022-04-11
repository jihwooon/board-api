//TODO : JWT을 이용한 로그인 기능 구현
// SessionController
// AuthenticationService
// AuthenticationInterceptor
// JWTUtil
// SessionRequest, SessionResponses DTO

package jpa.imform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void login() {

  }

}
