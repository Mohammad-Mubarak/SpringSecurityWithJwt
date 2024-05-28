package jwtauth.springsecurityjwt.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jwtauth.springsecurityjwt.JwtAuthService.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/***
 * This class is used to generate the JWT token and set it in the response header.
 * When User successfuly logged In then this class will generate the JWT token and set it in the response header.
 */

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenGenerator jwtTokenGenerator;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // Get the authentication object from the SecurityContextHolder bcz user is logged in Successfully.
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (null != authentication) {

      // Generate the JWT token and set it in the response header.
      String jwt = jwtTokenGenerator.generateToken(authentication.getName());
      System.out.println("token generated:===================================>" +jwt);
      response.setHeader("Authorization", jwt);
    }
    // Call the next filter in the filter chain.
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    // This method is used to check whether the filter should be applied or not. if false not applied
    return !request.getServletPath().equals("/login/user");
  }


}
