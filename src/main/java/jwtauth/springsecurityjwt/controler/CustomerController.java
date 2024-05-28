package jwtauth.springsecurityjwt.controler;


import jwtauth.springsecurityjwt.JwtAuthService.JwtTokenGenerator;
import jwtauth.springsecurityjwt.model.CustomerModel;

import jwtauth.springsecurityjwt.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  CustomerRepo customerRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenGenerator jwtToken;

  @GetMapping("/register/user")
  public String user() {
    return "Welcome User";
  }


  @GetMapping("/login/user")
  public String usertest() {
    return "Welcome User you are authenticated successfully";
  }

  @PostMapping("/register")
  public String registerUser(@RequestBody CustomerModel user) {
    if (customerRepo.findByEmail(user.getEmail()) != null) {
      return "User Already Exists";
    }

    if (user.getAge() < 18) {
      return "Age must be greater than 18";
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCreatedBy(user.getUsername());

    customerRepo.save(user);

    return "User Created Successfully";
  }
}
