package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long>{
    
}
