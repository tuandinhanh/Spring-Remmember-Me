package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.dao.RoleDao;
import com.anhtuan.springmvc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    public Role findByType(String type) {
        return roleDao.findByType(type);
    }

    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }
}
