package com.mapping.relationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.dto.LoginRequestDto;
import com.mapping.relationships.dto.PatentDto;
import com.mapping.relationships.service.UserService;

@CrossOrigin(origins =  "*")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginCredentialsDto> userLogin(@RequestBody LoginRequestDto emailAndPassword){
        LoginCredentialsDto login = new LoginCredentialsDto();
        login = userService.login(emailAndPassword.getEmail(), emailAndPassword.getPassword());

        if(login.getEmail()!=null){
            login.setMessage("Logged in successfully");
            return ResponseEntity.ok(login);
        }
        else{
            login.setMessage("The credential are invalid");
            return ResponseEntity.ok(login);
        }
    }


    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PatentDto dto){
        Long l = 0L;
       User u =  userService.addUser(dto, dto.getAccountType(), l);
        if(u==null)
        return ResponseEntity.ok("Something went worng");
        else
        return ResponseEntity.ok("The user is successfully added");
    }

  @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser( @PathVariable Long id ,@RequestBody PatentDto dto){
        userService.updateUserById(id, dto);

        return null;
    }

     @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable Long id){
      String res=   userService.deleteUserById(id);

        return ResponseEntity.ok(res);
    }

  
}
