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
import com.mapping.relationships.dto.PatentDto;
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
    public ResponseEntity<String> addDoctor(PatentDto dto) {
      String[] arrOfRoles = new String[]{"DOCTOR"};
      // getting the persised user object
      User savedUser = userService.addUser(dto, arrOfRoles, 0L);

      if(savedUser!= null){
      return ResponseEntity.ok("Success");
      }
      return ResponseEntity.ok("failure");
}


   @Override
   public ResponseEntity<List<PatentDto>> getDoctors() {

      //created a list to return in response
      List<PatentDto> list  = new ArrayList<>();


      //Fetched all the users who have doctor as their type
      List<User> users = userService.getUsers("DOCTOR");
      for(User u :users){

         List<Doctor> d = doctorDao.findByDoctorId(u.getUserId());
         if(!d.isEmpty()){
         PatentDto dto = new PatentDto();
         dto = modelMapper.map(u, PatentDto.class);
         list.add(dto);
         }
         
      }
      if(list.isEmpty())
      return null;
      return ResponseEntity.ok(list);

      
   }


   @Override
   public ResponseEntity<String> updateDoctor(PatentDto dto, Long id) {
      boolean flag =false;

      Optional<Doctor> doc = doctorDao.findById(id);
      if(doc.isPresent()){
      Optional<User> user = userService.findById(doc.get().getUser().getUserId());
      
      if(user.isPresent()){
        
          User u =  user.get();
          Doctor d = doc.get();
         u.setName(dto.getName());
         u.setEmail(dto.getEmail());
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