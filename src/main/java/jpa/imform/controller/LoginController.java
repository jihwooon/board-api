//TODO : 로그인 기능 구현
// 로그인 기능
// 로그 아웃 기능
package jpa.imform.controller;

import jpa.imform.dto.LoginDto;
import jpa.imform.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

  private final AuthenticationService authenticationService;

  public LoginController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.CREATED)
  public LoginDto.LoginResponse login(@RequestBody @Valid final LoginDto.LoginRequest request) {
    String email = request.getEmail();
    String password = request.getPassword();

    String accessToken = authenticationService.login(email, password);

    return LoginDto.LoginResponse.of(accessToken);
  }
}
