package com.mapping.relationships.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Patient;
import com.mapping.relationships.Entities.User;

public interface PatientDao extends JpaRepository<Patient, Long>{
    void deleteByUser(User u);

    Optional<Patient> findByUser(User u);
}
