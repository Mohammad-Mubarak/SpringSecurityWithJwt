package jwtauth.springsecurityjwt.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import jwtauth.springsecurityjwt.enums.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
public class BankAccountModel extends MetaData {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID accountNumber;

  private double balance;

  @Enumerated(value = EnumType.STRING)
  private AccountType accountType;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private CustomerModel customer;

  private Date openedDate;
}
