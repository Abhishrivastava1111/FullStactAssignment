package com.mapping.relationships.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dao.DoctorDao;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.service.DoctorService;
import com.mapping.relationships.service.UserService;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<String> addDoctor(DoctorDto dto) {
       
         User user = userService.addUser(dto);

         Doctor doc = new Doctor();
      doc.setSpecialization(dto.getSpecialization());
      doc.setUser(user);

     Doctor d =  doctorDao.save(doc);

     if(d!= null){
        return ResponseEntity.ok("Success");
     }
     return ResponseEntity.ok("failure");
}
}