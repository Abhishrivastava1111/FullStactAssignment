package com.mapping.relationships.service;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dto.DoctorDto;

public interface UserService {
    
    public User addUser(DoctorDto doctor);
}
