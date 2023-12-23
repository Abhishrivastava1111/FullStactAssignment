package com.mapping.relationships.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.relationships.Entities.Doctor;
import com.mapping.relationships.Entities.User;
import com.mapping.relationships.Entities.UserType;
import com.mapping.relationships.dao.DoctorDao;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.dto.DoctorDto;
import com.mapping.relationships.service.UserService;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;
    
    @Autowired
    private DoctorDao doctorDao;

    @Override
    public User addUser(DoctorDto dtoObj) {
      User newUser = new User();

      newUser.setName(dtoObj.getName()); 
      newUser.setUserType(UserType.DOCTOR); 
      newUser.setEmail(dtoObj.getEmail()); 
      newUser.setPassword(dtoObj.getPassword()); 

      return userDao.save(newUser);
    

}
}