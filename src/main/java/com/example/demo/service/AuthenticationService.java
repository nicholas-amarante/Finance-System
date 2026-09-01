package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.models.UserDetailsImpl;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new RuntimeException("Authentication object is null");
        }
        Object principal = authentication.getPrincipal();
        String email;
        if(principal instanceof UserDetailsImpl){
            email=((UserDetailsImpl) principal).getUsername();
        } else if (principal instanceof UserDetails) {
            email=((UserDetails) principal).getUsername();
        }else{
            email=principal.toString();
        }

        return userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found on DB"));
    }
}
