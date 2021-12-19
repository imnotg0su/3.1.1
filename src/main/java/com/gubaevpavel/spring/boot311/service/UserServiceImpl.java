package com.gubaevpavel.spring.boot311.service;


import com.gubaevpavel.spring.boot311.dao.UserDAO;
import com.gubaevpavel.spring.boot311.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional (readOnly = true)
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void edit(User user) {
        if (!user.getPassword().equals(getUserByName(user.getUsername()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.edit(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User userById(int id) {
        return userDAO.userById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }
}
