package jwtauth.springsecurityjwt.model;


import jakarta.persistence.Column;
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
import jwtauth.springsecurityjwt.enums.CreditCardType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "credit_card")
@NoArgsConstructor
public class CreditCardModel extends MetaData {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "card_number")
  private UUID cardNumber;

  private String cardHolderName;

  private Date expirationDate;

  private double creditLimit;

  private int cardUsed;

  private int cvv;

  private String issuer;


  @Enumerated(value = EnumType.STRING)
  private CreditCardType creditCardType;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private CustomerModel customer;

}

