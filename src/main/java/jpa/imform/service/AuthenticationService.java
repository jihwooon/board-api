package jpa.imform.service;

public interface AuthenticationService {

  String login(String email, String password);

  Long parseToken(String accessToken);
}
