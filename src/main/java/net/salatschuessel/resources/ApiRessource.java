package net.salatschuessel.resources;

import java.util.Collections;
import net.salatschuessel.config.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRessource {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @RequestMapping("/csrf")
  public CsrfToken csrf(final CsrfToken token) {
    return token;
  }

  @RequestMapping(value = "login", method = { RequestMethod.GET })
  public String login() {
    final String username = "user";
    final String password = "secret";
    this.authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    final String token = this.jwtTokenProvider.createToken(username,
        Collections.singletonList("USER"));
    return "{ \"token\": \"" + token + "\"}";
  }

}
