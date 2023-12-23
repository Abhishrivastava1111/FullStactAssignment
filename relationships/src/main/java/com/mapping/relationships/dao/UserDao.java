package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.User;

public interface UserDao extends JpaRepository<User, Long>{
    
}
