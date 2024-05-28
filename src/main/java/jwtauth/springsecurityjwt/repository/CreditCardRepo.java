package jwtauth.springsecurityjwt.repository;

import java.util.List;
import java.util.UUID;
import jwtauth.springsecurityjwt.model.CreditCardModel;
import jwtauth.springsecurityjwt.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCardModel, UUID> {
  CreditCardModel findByCardNumber(UUID cardNumber);

  List<CreditCardModel> findByCustomer(CustomerModel customerId);
}
