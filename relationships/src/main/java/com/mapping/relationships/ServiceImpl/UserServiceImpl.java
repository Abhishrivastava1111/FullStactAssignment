package com.mapping.relationships.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserType;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.dto.LoginCredentialsDto;
import com.mapping.relationships.service.UserService;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;
    
    @Override
    public User addUser(DoctorDto dtoObj) {
      User newUser = new User();

      newUser.setName(dtoObj.getName()); 
      newUser.setUserType(UserType.DOCTOR); 
      newUser.setEmail(dtoObj.getEmail()); 
      newUser.setPassword(dtoObj.getPassword()); 

      return userDao.save(newUser);
    

}

    @Override
    public List<User> getUsers() {
      return userDao.findByUserType(UserType.DOCTOR);
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

    @Override
    public LoginCredentialsDto login(String email, String passwordString) {
     List<User> u = userDao.findByEmailAndPassword(email, passwordString);
      LoginCredentialsDto userDataToBeSent = new LoginCredentialsDto();
      if(u.size()!=0){
      userDataToBeSent.setEmail(u.get(0).getEmail());
      userDataToBeSent.setRole(u.get(0).getUserType());
      userDataToBeSent.setAuthToken(passwordString);
      }
        return userDataToBeSent;
      
    }
}