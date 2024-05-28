package jwtauth.springsecurityjwt.controller;

import jwtauth.springsecurityjwt.enums.CreditCardType;
import jwtauth.springsecurityjwt.model.CreditCardModel;
import jwtauth.springsecurityjwt.model.CustomerModel;
import jwtauth.springsecurityjwt.repository.CreditCardRepo;
import jwtauth.springsecurityjwt.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CreditCardController {

  @Autowired
  private CreditCardRepo creditCardRepo;

  @Autowired
  private CustomerRepo customerRepo;

  @PostMapping("/creditcard/apply")
  public String applyCreditCard(Authentication authentication, @RequestBody String creditCardTypeStr) {
    String email = authentication.getName();

    CustomerModel customer = customerRepo.findByEmail(email);
    if (customer == null) {
      return "Customer not found";
    }

    CreditCardType creditCardType;
    try {
      creditCardType = CreditCardType.valueOf(creditCardTypeStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      return "Invalid credit card type";
    }

    CreditCardModel creditCardModel = new CreditCardModel();
    creditCardModel.setCreditCardType(creditCardType);
    creditCardModel.setCardHolderName(customer.getUsername());

    switch (creditCardType) {
      case VISA:
        creditCardModel.setCreditLimit(50000);
        break;
      case MASTERCARD:
        creditCardModel.setCreditLimit(100000);
        break;
      case AMERICAN_EXPRESS:
        creditCardModel.setCreditLimit(200000);
        break;
      case RUPAY:
        creditCardModel.setCreditLimit(75000);
        break;
      default:
        creditCardModel.setCreditLimit(0);
    }

    // Generate random 3-digit CVV
    Random random = new Random();
    int cvv = 100 + random.nextInt(900);
    creditCardModel.setCvv(cvv);
    creditCardModel.setIssuer("Bank of Spring Security");
    creditCardModel.setCustomer(customer);

    creditCardRepo.save(creditCardModel);

    return "Credit card applied successfully";
  }
}
