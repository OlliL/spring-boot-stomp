
package net.salatschuessel.config.jwt;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Bean
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("http://localhost"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "x-csrf-token"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .httpBasic().disable()
        .cors().and()
        .csrf().and()
        .headers()
        .and()
        .apply(new JwtConfigurer(this.jwtTokenProvider))
        .and()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/index.html").permitAll()
            .requestMatchers("/html/**").permitAll()
            .requestMatchers("/api/csrf").permitAll()
            .requestMatchers("/api/login").permitAll()
            .requestMatchers("/websocket").permitAll()
            .requestMatchers("/secured/test").authenticated()
            .requestMatchers("/**").denyAll()
            .anyRequest().authenticated()
        );
    //@formatter:on
    return http.build();
  }
}