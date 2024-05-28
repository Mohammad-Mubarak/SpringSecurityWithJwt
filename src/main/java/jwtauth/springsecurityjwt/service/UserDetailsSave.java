//package jwtauth.springsecurityjwt.service;
//
//import jwtauth.springsecurityjwt.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class UserDetailsSave implements UserDetailsService {
//  @Autowired
//  UserRepo userRepo;
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserDetails userDetails = userRepo.findByUsername(username);
//
//    System.out.println("UserDetailsSave.loadUserByUsername======================================" + userDetails.getUsername() + userDetails.getAuthorities());
//    if (userDetails != null) {
//      return userDetails;
//    }
//    return null;
//  }
//}
