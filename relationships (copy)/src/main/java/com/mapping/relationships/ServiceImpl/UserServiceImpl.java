package com.mapping.relationships.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapping.relationships.Entities.Compounder;
import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.Patient;
import com.mapping.relationships.Entities.Roles;
import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserRole;
import com.mapping.relationships.dao.CompounderDao;
import com.mapping.relationships.dao.DoctorDao;
import com.mapping.relationships.dao.PatientDao;
import com.mapping.relationships.dao.RolesDao;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.dto.PatentDto;
import com.mapping.relationships.service.UserService;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private CompounderDao compounderDao;

    @Autowired
    private DoctorDao doctorDao;

// Add user function ********************************************
    @Transactional
    @Override
    public User addUser(DoctorDto dtoObj, String[] type, Long id) {
      User newUser = new User();
      
      Roles givenRole =null;
      newUser.setName(dtoObj.getName()); 
      newUser.setEmail(dtoObj.getEmail()); 
      newUser.setPassword(dtoObj.getPassword()); 
      for(int i =0; i<type.length; i++){
          givenRole = rolesDao.findByRoleName(type[i]).get();
            // .orElseGet(() -> {
            //     Roles newRole = new Roles(type[i]);
            //     return newRole; 
            // });
            

      newUser.getRoles().add(givenRole);
          }
      //saving the user with the given credential
      User savedUser =  userDao.save(newUser);

        // Creating and persisting the approprite  obj to persist in the database as the user
        for(int i =0; i<type.length; i++){

         switch (type[i]) {


          case "PATIENT":
             Patient pat = new Patient();
             pat.setUser(savedUser);
             patientDao.save(pat);
            break;

          case "DOCTOR":
                 Doctor doc = new Doctor();
                 doc.setSpecialization(dtoObj.getSpecialization());
                 doc.setUser(savedUser);
                 doctorDao.save(doc);

            
            break;

          case "COMPOUNDER":
                Compounder compounder = new Compounder();
                compounder.setUser(savedUser);
                compounderDao.save(compounder);
            break;
      
          default:
          return null;
         }
      //persisting the userRole for the perticular type of user
      UserRole userRole = new UserRole();
      userRole.setUser(savedUser);

      userRole.setRole(givenRole);
         }
     
        
   

      

      return savedUser;
    

}

   

    @Override
    public Optional<User> findById(Long id) {
     return userDao.findById(id);
    }

    @Override
    public void save(User u) {
     userDao.save(u);
    }

    @Override
    public void deleteById(Long id) {
      userDao.deleteById(id);
    }

    @Transactional
    @Override
    public LoginCredentialsDto login(String email, String passwordString) {
    List<User> u = userDao.findByEmailAndPassword(email, passwordString);
    LoginCredentialsDto userDataToBeSent = new LoginCredentialsDto();
      
      
      if(u.size()!=0){
      userDataToBeSent.setEmail(u.get(0).getEmail());
      
      u.get(0).getRoles().forEach(role-> userDataToBeSent.getRole().add(role.getRoleName()));
      userDataToBeSent.setAuthToken(passwordString);
      }
        return userDataToBeSent;
      
    }




    @Override
    public List<User> getUsers(String ...type) {
       List<User>  allDoctors = new ArrayList<>();
      for(String t : type){

      Roles doctorRoles = rolesDao.findByRoleName(t).get();
      List<User> u = userDao.findByRolesIn(Collections.singleton(doctorRoles) );
        if(u.size()!=0){
          allDoctors.addAll(u);
        }
      }



      return  allDoctors;
    }



    @Override
    public void updateUserById(Long id, PatentDto dto) {
     Optional<User> persistedUser =  userDao.findById(id);
      // if()
    }



    



}