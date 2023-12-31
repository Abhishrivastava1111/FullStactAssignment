package com.mapping.relationships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.relationships.dto.PatentDto;
import com.mapping.relationships.service.DoctorService;
@CrossOrigin(origins =  "*")
@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody PatentDto dto){
    

        return doctorService.addDoctor(dto);
    }

    @GetMapping("/allDoctors")
    public ResponseEntity<List<PatentDto>> getDoctors(){

        return doctorService.getDoctors();
    }

    @PutMapping("/editDoctor/{id}")
    public ResponseEntity<String> updateDoctor(@RequestBody PatentDto updatedDto, @PathVariable Long id){

        return doctorService.updateDoctor(updatedDto, id);
    }


    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<String> deleteDoctorsRecord(@PathVariable Long id){
        
        return doctorService.deleteDoctorsRecord(id);
    }

}
