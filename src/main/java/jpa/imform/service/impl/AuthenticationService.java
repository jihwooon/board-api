package jpa.imform.service.impl;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class AuthenticationService {

  public String login() {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    return "a.b.c";
  }
}
