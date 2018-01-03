package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySso(String sso);

    List<User> fillAllUsers();
}
