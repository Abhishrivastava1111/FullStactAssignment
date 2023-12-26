package com.mapping.relationships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.service.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers(){

       List<User> usersList =  adminService.getAllUsers();
        
       
       return ResponseEntity.ok(usersList);

    }
    
  
}
