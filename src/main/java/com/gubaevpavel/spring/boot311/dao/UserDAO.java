package com.gubaevpavel.spring.boot311.dao;



import com.gubaevpavel.spring.boot311.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void add (User user);
    void delete (int id);
    void edit (User user);
    User userById (int id);
    User getUserByName (String username);
}
