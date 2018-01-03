package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.Role;

import java.util.List;

public interface RoleService {

    Role findById(Integer id);

    Role findByType(String type);

    List<Role> findAllRoles();
}
