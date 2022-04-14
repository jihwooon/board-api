package jpa.imform.interceptor;

import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

  private AuthenticationServiceImpl authenticationService;

  public AuthenticationInterceptor(AuthenticationServiceImpl authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    return true;
  }
}
