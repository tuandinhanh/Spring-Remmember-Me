package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySso(String sso);

    void save(User user);

    void updateUser(User user);

    void deleteBySso(String sso);

    List<User> findAllUsers();

    boolean isUserSsoUnique(Integer id, String sso);
}
