package com.mapping.relationships.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

      // getting the persised user object
      User savedUser = userService.addUser(dto, "DOCTOR");

      if(savedUser!= null){
      return ResponseEntity.ok("Success");
      }
      return ResponseEntity.ok("failure");
}


   @Override
   public ResponseEntity<List<DoctorDto>> getDoctors() {

      //created a list to return in response
      List<DoctorDto> list  = new ArrayList<>();


      //Fetched all the users who have doctor as their type
      List<User> users = userService.getUsers("DOCTOR");
      for(User u :users){

         List<Doctor> d = doctorDao.findByDoctorId(u.getUserId());
         if(!d.isEmpty()){
         DoctorDto dto = new DoctorDto();
         dto = modelMapper.map(u, DoctorDto.class);
         dto.setSpecialization(d.get(0).getSpecialization());
         list.add(dto);
         }
         
      }
      if(list.isEmpty())
      return null;
      return ResponseEntity.ok(list);

      
   }


   @Override
   public ResponseEntity<String> updateDoctor(DoctorDto dto, Long id) {
      boolean flag =false;

      Optional<Doctor> doc = doctorDao.findById(id);
      if(doc.isPresent()){
      Optional<User> user = userService.findByDoctorId(doc.get().getUser().getUserId());
      
      if(user.isPresent()){
        
          User u =  user.get();
          Doctor d = doc.get();
         u.setName(dto.getName());
         u.setEmail(dto.getEmail());
         d.setSpecialization(dto.getSpecialization());
         doctorDao.save(d);
         userService.save(u);
         flag = true;
      }
   }
      return flag?ResponseEntity.ok("The data is updated successfully"):ResponseEntity.ok("something went wrong");

   }


   @Override
   public ResponseEntity<String> deleteDoctorsRecord(Long id) {
      Optional<Doctor> doctorTobeDeleted = doctorDao.findById(id);
      if(doctorTobeDeleted.isPresent()){
         doctorDao.deleteById(id);
         userService.deleteById(doctorTobeDeleted.get().getUser().getUserId());
      }
     
      

      
      return ResponseEntity.ok("deleted successfully");
   }
}