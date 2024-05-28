package jwtauth.springsecurityjwt.configuration;


import java.util.Arrays;
import jwtauth.springsecurityjwt.Filter.JwtTokenGeneratorFilter;
import jwtauth.springsecurityjwt.Filter.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtValidator jwtAuthFilter;

  @Autowired
  private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;

  @Bean
  public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated()
        ).addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
        .httpBasic(Customizer.withDefaults())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
          .httpBasic(Customizer.withDefaults());


    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setExposedHeaders(Arrays.asList("Authorization"));
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  //Way 2
  //        .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//          CorsConfiguration config = new CorsConfiguration();
//          config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
//          config.setAllowedMethods(Collections.singletonList("*"));
//          config.setAllowCredentials(true);
//          config.setAllowedHeaders(Collections.singletonList("*"));
//          config.setExposedHeaders(Arrays.asList("Authorization"));
//          config.setMaxAge(3600L);
//          return config;
//        }))
}
