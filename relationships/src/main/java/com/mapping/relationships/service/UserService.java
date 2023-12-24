package com.mapping.relationships.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.dto.LoginCredentialsDto;

public interface UserService {
    
    public User addUser(DoctorDto doctor);

    public List<User> getUsers();

    public Optional<User> findByDoctorId(Long id);

    public void save(User u);

    public void deleteById(Long id);


    public LoginCredentialsDto login(String email, String passwordString);
}
