//package jwtauth.springsecurityjwt.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import java.util.Collection;
//import java.util.List;
//import java.util.UUID;
//import jwtauth.springsecurityjwt.enums.Role;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//@Entity
//@Table(name = "UserTable")
//@Getter
//@Setter
//@NoArgsConstructor
//public class UserModel implements UserDetails {
//  @Id
//  @GeneratedValue(strategy = GenerationType.UUID)
//  @Column(name = "user_id")
//  private UUID id;
//
//  private String username;
//  private String password;
//  private String email;
//  private String phone;
//  private String city;
//  private int age;
//
//  @Enumerated(value = EnumType.STRING)
//  @Column(name = "user_role")
//  private Role role;
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
////    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
////    for (Role roles : this.role){
////          authorities.add(new SimpleGrantedAuthority(roles.name()));
////    }
//    return List.of(new SimpleGrantedAuthority(role.name()));
//  }
//
//
//
//  @Override
//  public String getUsername() {
//    return this.username;
//  }
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
//}
