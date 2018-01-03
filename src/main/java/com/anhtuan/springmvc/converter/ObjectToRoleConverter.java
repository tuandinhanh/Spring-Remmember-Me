package com.anhtuan.springmvc.converter;

import com.anhtuan.springmvc.model.Role;
import com.anhtuan.springmvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ObjectToRoleConverter implements Converter<Object, Role> {

    @Autowired
    RoleService roleService;

    public Role convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        Role role = roleService.findById(id);
        return role;
    }
}
