
package net.salatschuessel.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {
  private final JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(final JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(final ServletRequest req, final ServletResponse res,
      final FilterChain filterChain) throws IOException, ServletException {
    final String token = this.jwtTokenProvider.resolveToken((HttpServletRequest) req);
    if (token != null && this.jwtTokenProvider.validateToken(token)) {
      final Authentication auth = this.jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(req, res);
  }
}