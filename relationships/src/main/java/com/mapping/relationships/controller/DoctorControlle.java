package com.mapping.relationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.service.DoctorService;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorControlle {
    
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorDto dto){

        return doctorService.addDoctor(dto);
    }
}
