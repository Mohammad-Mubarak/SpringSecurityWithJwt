package jwtauth.springsecurityjwt.provider;

import jwtauth.springsecurityjwt.model.CustomerModel;
import jwtauth.springsecurityjwt.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthenticationProviderJwt implements AuthenticationProvider {

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    log.info("Authenticating user==============================>>>>>>>>>>>>>>>>>>>>: " + email);
    CustomerModel user = customerRepo.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("User not registered");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Password not correct");
    }
    log.info("Password Authenticated Successfully");
    return new UsernamePasswordAuthenticationToken(
        user.getEmail(),
        user.getPassword(),
        user.getAuthorities()
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
