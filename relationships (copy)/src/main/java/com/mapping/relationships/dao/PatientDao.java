package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Patient;

public interface PatientDao extends JpaRepository<Patient, Long>{
    
}
