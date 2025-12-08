package com.example.demo.controller;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.LoginUserDTO;
import com.example.demo.dto.RecoveryJwtTokenDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> authenticateUser(@RequestBody LoginUserDTO loginUserDTO) {
        RecoveryJwtTokenDTO token=userService.authenticateUser(loginUserDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest(){
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest(){
        return new ResponseEntity<>("Cliente autenticado com sucesso",HttpStatus.OK);
    }

    @GetMapping("/test/admin")
    public ResponseEntity<String> getAdminAuthenticationTest(){
        return new ResponseEntity<>("Admin autenticado com sucesso",HttpStatus.OK);
    }
}
