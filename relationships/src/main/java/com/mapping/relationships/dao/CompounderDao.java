package com.mapping.relationships.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Compounder;
import com.mapping.relationships.Entities.User;

public interface CompounderDao extends JpaRepository<Compounder, Long>{
    void deleteByUser(User u);

    Optional<Compounder> findByUser(User savedUser);
   
}
