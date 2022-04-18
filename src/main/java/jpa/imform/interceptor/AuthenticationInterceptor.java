package jpa.imform.interceptor;

import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

  private AuthenticationServiceImpl authenticationServiceImpl;

  public AuthenticationInterceptor(AuthenticationServiceImpl authenticationServiceImpl) {
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {

    return (filterPathAndMethod(request)) ||
        doAuthentication(request, response);
  }

  private boolean filterPathAndMethod(HttpServletRequest request) {

    String method = request.getMethod();
    if (method.equals("GET")) {
      return true;
    }

    String path = request.getRequestURI();
    if (!path.equals("/members")) {
      return true;
    }
    return false;
  }

  private boolean doAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authorization = request.getHeader("Authorization");

    if (authorization == null) {
      response.sendError(HttpStatus.UNAUTHORIZED.value());
      return false;
    }

    String accessToken = authorization.substring("Bearer ".length());
    Long memberId = authenticationServiceImpl.parseToken(accessToken);

    return true;
  }
}
