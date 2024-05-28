package jwtauth.springsecurityjwt.repository;

import java.util.List;
import java.util.UUID;
import jwtauth.springsecurityjwt.model.BankAccountModel;
import jwtauth.springsecurityjwt.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankAccountRepo extends JpaRepository<BankAccountModel, UUID> {
  BankAccountModel findByAccountNumber(UUID accountNumber);
  // Find all bank accounts belonging to a specific customer
  List<BankAccountModel> findByCustomer(CustomerModel customer);
}
