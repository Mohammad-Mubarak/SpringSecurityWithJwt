package jwtauth.springsecurityjwt.configuration;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("auditorAware")
@Slf4j
public class AuditingConfig implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    log.info("Getting the current user===========>>" + SecurityContextHolder.getContext()
        .getAuthentication().getName());
    return Optional.ofNullable(SecurityContextHolder.getContext()
        .getAuthentication().getName());
  }
}
