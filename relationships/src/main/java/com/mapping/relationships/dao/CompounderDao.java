package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Compounder;

public interface CompounderDao extends JpaRepository<Compounder, Long>{
    
}
