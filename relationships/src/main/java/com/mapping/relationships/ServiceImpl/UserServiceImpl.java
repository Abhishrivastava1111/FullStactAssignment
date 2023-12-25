package com.mapping.relationships.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.Roles;
import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserRole;
import com.mapping.relationships.dao.DoctorDao;
import com.mapping.relationships.dao.RolesDao;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.service.UserService;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private DoctorDao doctorDao;





    @Transactional
    @Override
    public User addUser(DoctorDto dtoObj, String type) {
      User newUser = new User();

      newUser.setName(dtoObj.getName()); 
      newUser.setEmail(dtoObj.getEmail()); 
      newUser.setPassword(dtoObj.getPassword()); 

      Roles givenRole = rolesDao.findByRoleName(type)
            .orElseGet(() -> {
                Roles newRole = new Roles(type);
                return newRole; 
            });

      newUser.getRoles().add(givenRole);

      //saving the user with the given credential
      User savedUser =  userDao.save(newUser);

        // Creating the doc obj to persist in the database as the user
         Doctor doc = new Doctor();
         
         doc.setSpecialization(dtoObj.getSpecialization());

         //setting up the field of doctor as user
         doc.setUser(savedUser);

   //actually persisting the data in the doctor's table
      doctorDao.save(doc);

      //persisting the userRole for the perticular type of user
      UserRole userRole = new UserRole();
      userRole.setUser(savedUser);

      userRole.setRole(givenRole);

      return savedUser;
    

}

   

    @Override
    public Optional<User> findByDoctorId(Long id) {
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
}