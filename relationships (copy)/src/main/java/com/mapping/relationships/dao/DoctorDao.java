package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Doctor;
import java.util.List;


public interface DoctorDao extends JpaRepository<Doctor, Long>{

    List<Doctor> findByDoctorId(Long doctorId);
    
}
