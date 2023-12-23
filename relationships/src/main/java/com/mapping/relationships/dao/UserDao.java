package com.mapping.relationships.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserType;

public interface UserDao extends JpaRepository<User, Long>{

    List<User> findByUserType(UserType doctor);
    
}
