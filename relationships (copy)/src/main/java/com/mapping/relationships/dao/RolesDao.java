package com.mapping.relationships.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Roles;

public interface RolesDao extends JpaRepository<Roles, Long>{
    
    Optional<Roles> findByRoleName(String roleName);
}
