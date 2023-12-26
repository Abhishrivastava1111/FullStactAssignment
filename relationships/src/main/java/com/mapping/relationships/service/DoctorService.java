package com.mapping.relationships.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mapping.relationships.dto.PatentDto;


public interface DoctorService {
    public ResponseEntity<String> addDoctor(PatentDto dto);

    public ResponseEntity<List<PatentDto>> getDoctors();

    public ResponseEntity<String> updateDoctor(PatentDto dto, Long id);

    public ResponseEntity<String> deleteDoctorsRecord(Long id);
}
