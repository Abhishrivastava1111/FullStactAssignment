package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, Long>{

    void deleteByUser(User u);
}
