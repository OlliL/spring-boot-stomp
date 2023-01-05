
package net.salatschuessel.config.jwt;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final String password = this.encoder.encode("secret");

    final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
    final org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
        "user", password, grantedAuthorities);
    return userDetails;
  }
}