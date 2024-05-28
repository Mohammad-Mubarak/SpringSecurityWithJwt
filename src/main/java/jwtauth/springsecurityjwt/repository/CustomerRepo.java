package jwtauth.springsecurityjwt.repository;

import java.util.UUID;
import jwtauth.springsecurityjwt.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel, UUID> {
       CustomerModel findByUsername(String username);
       CustomerModel findByEmail(String email);
}
