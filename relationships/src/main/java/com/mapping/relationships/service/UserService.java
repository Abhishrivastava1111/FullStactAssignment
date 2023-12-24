package com.mapping.relationships.service;

import java.util.List;
import java.util.Optional;


import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.dto.LoginCredentialsDto;

public interface UserService {
    
    public User addUser(DoctorDto doctor, String string);


    public Optional<User> findByDoctorId(Long id);

    public void save(User u);

    public void deleteById(Long id);


    public LoginCredentialsDto login(String email, String passwordString);


    public List<User> getUsers(String ...type);
}
