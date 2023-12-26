package com.mapping.relationships.service;

import java.util.List;
import java.util.Optional;


import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.dto.PatentDto;

public interface UserService {
    
    public User addUser(PatentDto dto, String[] strings, Long id);


    public Optional<User> findById(Long id);

    public void save(User u);

    public void deleteById(Long id);


    public LoginCredentialsDto login(String email, String passwordString);


    public List<User> getUsers(String ...type);


    public void updateUserById(Long id, PatentDto dto);


    public String deleteUserById(Long id);
}
