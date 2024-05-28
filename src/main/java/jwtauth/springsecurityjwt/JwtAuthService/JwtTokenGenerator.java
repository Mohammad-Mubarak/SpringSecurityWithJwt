package jwtauth.springsecurityjwt.JwtAuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenGenerator {

  public String secretkey = "wH9g8GQ2n9c7mFQ6jA3uLm8vh9N5ZP1/Tu2qCluJx34=";

  public String generateToken(String email){
    return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(getSecretkey())
        .compact();
  }

  public Boolean isTokenExpired(String token){
    final Date expiration = getClaimFromToken(token,Claims::getExpiration);
    return expiration.before(new Date());
  }


  public Boolean validateToken(String token, String emailAuth){
    final String email = getUserByEmail(token);
    return (email.equals(emailAuth) && !isTokenExpired(token));
  }

  public Claims extractClaimFromTOken(String token){
      return Jwts.parser()
          .verifyWith(getSecretkey())
          .build()
          .parseSignedClaims(token)
          .getPayload();
  }


  public String getUserByEmail(String token){
    return getClaimFromToken(token,Claims::getSubject);
  }
  public <T> T getClaimFromToken(String token, Function<Claims,T> resolver){
    final Claims claims = extractClaimFromTOken(token);
    return resolver.apply(claims);
  }
  public SecretKey getSecretkey() {
    byte[] decodedKey = Decoders.BASE64.decode(secretkey);
    return Keys.hmacShaKeyFor(decodedKey);
  }
}
