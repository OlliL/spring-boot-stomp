package net.salatschuessel.config.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
@ConditionalOnProperty(name = "websocket.csrf.enable", havingValue = "0", matchIfMissing = false)
public class WebSocketSecurityConfigWithoutCsrf
    extends AbstractSecurityWebSocketMessageBrokerConfigurer {

  @Override
  protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
    messages.anyMessage().authenticated();
  }

  @Override
  protected boolean sameOriginDisabled() {
    return true;
  }

}