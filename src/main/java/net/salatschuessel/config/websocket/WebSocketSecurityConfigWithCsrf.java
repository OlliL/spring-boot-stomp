package net.salatschuessel.config.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

@Configuration
@EnableWebSocketSecurity
@ConditionalOnProperty(name = "websocket.csrf.enable", havingValue = "1", matchIfMissing = false)
public class WebSocketSecurityConfigWithCsrf {

  @Bean
  AuthorizationManager<Message<?>> messageAuthorizationManager(
      final MessageMatcherDelegatingAuthorizationManager.Builder messages) {
    messages.nullDestMatcher().authenticated();
    messages.anyMessage().authenticated();

    return messages.build();
  }

}
