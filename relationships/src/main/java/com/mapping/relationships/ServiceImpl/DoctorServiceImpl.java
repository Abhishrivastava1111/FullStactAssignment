package com.mapping.relationships.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper  modelMapper;

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


   @Override
   public ResponseEntity<List<DoctorDto>> getDoctors() {

      //created a list to return in response
      List<DoctorDto> list  = new ArrayList<>();


      //Fetched all the users who have doctor as their type
      List<User> users = userService.getUsers();
      for(User u :users){
         Doctor d = doctorDao.findByDoctorId(u.getUserId()).get(0);
         DoctorDto dto = new DoctorDto();
         dto = modelMapper.map(u, DoctorDto.class);
         dto.setSpecialization(d.getSpecialization());
         list.add(dto);
      }
      
      return ResponseEntity.ok(list);

      
   }
}