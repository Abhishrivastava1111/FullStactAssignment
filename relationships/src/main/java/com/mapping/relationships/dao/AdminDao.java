package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Admin;

public interface AdminDao extends JpaRepository<Admin,Long>{
    
}
