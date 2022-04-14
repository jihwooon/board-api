package jpa.imform.service;

public interface AuthenticationService {

  String login();

  Long parseToken(String accessToken);

}
