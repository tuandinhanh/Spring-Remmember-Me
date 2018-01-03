package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Role;

import java.util.List;

public interface RoleDao {

    Role findById(int id);

    Role findByType(String type);

    List<Role> findAllRoles();
}
