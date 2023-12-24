package com.mapping.relationships.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Roles;
import com.mapping.relationships.Entities.User;


public interface UserDao extends JpaRepository<User, Long>{
    List<User> findByRolesIn(Collection<Roles> roles);

    List<User> findByEmailAndPassword(String email, String password);
}
