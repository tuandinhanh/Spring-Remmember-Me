package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.dao.UserDao;
import com.anhtuan.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findBySso(String sso) {
        return userDao.findBySSO(sso);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public void updateUser(User user) {
        User entity = userDao.findById(user.getId());
        if (entity != null) {
            entity.setSsoId(user.getSsoId());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setRoles(user.getRoles());
            entity.setState(user.getState());
        }
    }

    public void deleteBySso(String sso) {
        userDao.deleteBySso(sso);
    }

    public List<User> findAllUsers() {
        return userDao.fillAllUsers();
    }

    public boolean isUserSsoUnique(Integer id, String sso) {
        User user = findBySso(sso);
        return (user == null || ((id != null ) && (user.getId() == id)));
    }
}
