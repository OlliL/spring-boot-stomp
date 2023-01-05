package net.salatschuessel.config.websocket;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

// FIXME: Fix for spring-security/issues/12378
// can be used instead of requestHandler.setCsrfRequestAttributeName(null);
public class EagerCsrfTokenHandshakeInterceptor implements HandshakeInterceptor {

  @Override
  public boolean beforeHandshake(final ServerHttpRequest request, final ServerHttpResponse response,
      final WebSocketHandler wsHandler, final Map<String, Object> attributes) {
    final HttpServletRequest httpRequest = ((ServletServerHttpRequest) request).getServletRequest();
    CsrfToken token = (CsrfToken) httpRequest.getAttribute(CsrfToken.class.getName());
    if (token == null) {
      return true;
    }

    token = new DefaultCsrfToken(token.getHeaderName(), token.getParameterName(), token.getToken());
    attributes.put(CsrfToken.class.getName(), token);
    return true;
  }

  @Override
  public void afterHandshake(final ServerHttpRequest request, final ServerHttpResponse response,
      final WebSocketHandler wsHandler, final Exception exception) {

  }
}