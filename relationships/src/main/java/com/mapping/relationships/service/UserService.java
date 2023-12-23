package com.mapping.relationships.service;

import java.util.List;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.DoctorDto;

public interface UserService {
    
    public User addUser(DoctorDto doctor);

    public List<User> getUsers();
}
