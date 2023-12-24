package com.mapping.relationships.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Roles;
import com.mapping.relationships.Entities.User;


public interface UserDao extends JpaRepository<User, Long>{

    List<User> findByEmailAndPassword(String email, String password);

    List<User> findByRolesIn(Set<Roles> singleton);
}
