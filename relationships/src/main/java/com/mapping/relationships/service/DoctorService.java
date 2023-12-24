package com.mapping.relationships.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mapping.relationships.dto.DoctorDto;

public interface DoctorService {
    public ResponseEntity<String> addDoctor(DoctorDto dto);

    public ResponseEntity<List<DoctorDto>> getDoctors();

    public ResponseEntity<String> updateDoctor(DoctorDto dto, Long id);

    public ResponseEntity<String> deleteDoctorsRecord(Long id);
}
