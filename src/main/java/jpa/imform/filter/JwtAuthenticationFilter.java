package jpa.imform.filter;

import jpa.imform.error.InvalidTokenException;
import jpa.imform.service.impl.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final AuthenticationService authenticationService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   AuthenticationService authenticationService) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        if (filterPathAndMethod(request)) {
            chain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        if (authorization == null) {
            throw new InvalidTokenException("");
        }

        String accessToken = authorization.substring("Bearer ".length());

        authenticationService.parseToken(accessToken);

        chain.doFilter(request, response);
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
}
