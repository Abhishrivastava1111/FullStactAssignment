package com.mapping.relationships.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.relationships.Entities.User;
import com.mapping.relationships.dao.UserDao;
import com.mapping.relationships.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    UserDao userDaoDao;


    @Override
    public List<User> getAllUsers() {
       return userDaoDao.findAll();
    }
    
}
