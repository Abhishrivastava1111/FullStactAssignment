package com.mapping.relationships.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.User;

import java.util.List;
import java.util.Optional;


public interface DoctorDao extends JpaRepository<Doctor, Long>{

    List<Doctor> findByDoctorId(Long doctorId);

void deleteByUser(User u);

Optional<Doctor> findByUser(User savedUser);
}
