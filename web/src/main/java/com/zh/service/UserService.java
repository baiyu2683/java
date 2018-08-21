package com.zh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zh.dao.UserDao;
import com.zh.domain.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    public List<User> getAll() {
        return userDao.getAll();
    }
    
    public void add(User user) {
        User newUser = userDao.add(user);
        System.out.println(newUser.getId());
    }
    
    @Transactional
    public void add(User user1, User user2) {
        add(user1);
        add(user2);
    }
    
    public int delete(String[] ids) {
        int rows = 0;
        for (String id : ids) {
            rows += userDao.remove(Integer.parseInt(id));
        }
        return rows;
    }
    
    public int update(User user) {
        return userDao.update(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    
}
