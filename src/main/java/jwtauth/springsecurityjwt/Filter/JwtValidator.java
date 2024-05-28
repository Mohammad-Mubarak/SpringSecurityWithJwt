package jwtauth.springsecurityjwt.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jwtauth.springsecurityjwt.JwtAuthService.JwtTokenGenerator;
import jwtauth.springsecurityjwt.model.CustomerModel;
import jwtauth.springsecurityjwt.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class JwtValidator extends OncePerRequestFilter {

  @Autowired
  private JwtTokenGenerator jwtToken;

  @Autowired
  private CustomerRepo customerRepo;

  /***
   *
   * This method is used to verify the JWT token and set the authentication object in the SecurityContextHolder.
   *After successful login this method will verify the JWT token and set the authentication object in the SecurityContextHolder.
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String token;
    String email;

    // Check if the request has a token in the header
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    token = authHeader.substring(7);
    email = jwtToken.getUserByEmail(token);

    // Check if the token is valid and the user is not already authenticated
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      CustomerModel user = customerRepo.findByEmail(email);
      if (Boolean.TRUE.equals(jwtToken.validateToken(token, user.getEmail()))) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities());
//        authentication.setDetails(
//            new WebAuthenticationDetailsSource().buildDetails(request)
//        );

        log.info("Successfully authenticated user: =================================" + email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }

  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/login/user");
  }
}
