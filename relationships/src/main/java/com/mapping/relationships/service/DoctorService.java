package com.mapping.relationships.service;

import org.springframework.http.ResponseEntity;

import com.mapping.relationships.dto.DoctorDto;

public interface DoctorService {
    public ResponseEntity<String> addDoctor(DoctorDto dto);
}
