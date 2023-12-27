package com.mapping.relationships.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mapping.relationships.Entities.Compounder;
import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.Patient;
import com.mapping.relationships.Entities.Roles;
import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserRole;
import com.mapping.relationships.dao.AdminDao;
import com.mapping.relationships.dao.CompounderDao;
import com.mapping.relationships.dao.DoctorDao;
import com.mapping.relationships.dao.PatientDao;
import com.mapping.relationships.dao.RolesDao;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.dao.UserRoleDao;
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

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private AdminDao adminDao;

// Add user function ********************************************
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User addUser(PatentDto dtoObj, String[] type, Long id) {

      User newUser = null;
     Optional<User> u =  userDao.findById(id);
     
      if(u.isPresent()){
       
        newUser= u.get();
      }
      else{
        if(id!=0)// exception to be thrown
        return null;
        else
        newUser = new User();
      }

      Roles givenRole =null;
      newUser.setName(dtoObj.getName()); 
      newUser.setEmail(dtoObj.getEmail()); 
      newUser.setPassword(dtoObj.getPassword()); 
      for (int i = 0; i < type.length; i++) {
        givenRole = rolesDao.findByRoleName(type[i]).orElse(null);
    
        if (givenRole != null && !newUser.getRoles().contains(givenRole)) {
            newUser.getRoles().add(givenRole);
        }
    }
    
      //saving the user with the given credential
      User savedUser =  userDao.save(newUser);

        // Creating and persisting the approprite  obj to persist in the database as the user
        for(int i =0; i<type.length; i++){

         switch (type[i]) {


          case "PATIENT":
          Patient p = patientDao.findByUser(savedUser).orElse(null);
             if(p==null){
             Patient pat = new Patient();
             pat.setUser(savedUser);
             patientDao.save(pat);}
            break;

          case "DOCTOR":
          Doctor d = doctorDao.findByUser(savedUser).orElse(null);
                 if(d==null)
                 {Doctor doc = new Doctor();
                 doc.setUser(savedUser);
                 doctorDao.save(doc);}

            
            break;

          case "COMPOUNDER":
          Compounder c = compounderDao.findByUser(savedUser).orElse(null);
                if (c == null) {
                Compounder compounder = new Compounder();
                compounder.setUser(savedUser);
                compounderDao.save(compounder);
                }
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

   // Update by Id of user **********************************************************
   @Transactional(propagation = Propagation.REQUIRED) 
   @Override
    public void updateUserById(Long id, PatentDto dto) {

      addUser(dto, dto.getAccountType(), id);
    
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
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String deleteUserById(Long id) {
        Optional<User> userOptional = userDao.findById(id);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
    
            compounderDao.deleteByUser(user);
            doctorDao.deleteByUser(user);
            patientDao.deleteByUser(user);
            adminDao.deleteByUser(user);
    
            rolesDao.deleteByUsers(user);
            user.setRoles(null); 
    
            userRoleDao.deleteByUser(user);
    
            // Delete the user
            userDao.delete(user);
    
            return "Success";
        } else {
            return "Failure: User not found";
        }
    }
    



    



}