package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Admin;
import com.mapping.relationships.Entities.User;

public interface AdminDao extends JpaRepository<Admin,Long>{
    void deleteByUser(User u);
}
