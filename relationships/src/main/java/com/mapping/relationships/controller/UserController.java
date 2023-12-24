package com.mapping.relationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginCredentialsDto> userLogin(String email, String password){
        LoginCredentialsDto login = new LoginCredentialsDto();
        login = userService.login(email, password);

        if(login.getEmail()!=null){
            login.setMessage("Logged in successfully");
            return ResponseEntity.ok(login);
        }
        else{
            login.setMessage("The credential are invalid");
            return ResponseEntity.ok(login);
        }
    }
}
