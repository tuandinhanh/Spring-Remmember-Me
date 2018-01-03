package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) Hibernate.initialize(user.getRoles());
        return user;
    }

    public User findBySSO(String sso) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("ssoId"), sso));
        try {
            User user = (User) getSession().createQuery(criteriaQuery).getSingleResult();
            Hibernate.initialize(user.getRoles());
            return user;
        } catch (NoResultException e) {
            System.out.println("Entity not found!");
            return null;
        }
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteBySso(String sso) {
        User user = findBySSO(sso);
        if (user != null) delete(user);
        /*we don't have delete join table due to manytomany set User is ower.
        * The entity you put 'mappedBy' is the one which is NOT the owner.
        * entityManager.remove(role)
        * for (User user : role.getUsers) {
        * user.getRoles.remove(group);
}*/
    }

    public List<User> fillAllUsers() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).orderBy(criteriaBuilder.asc(userRoot.get("firstName")));
        List<User> users = getSession().createQuery(criteriaQuery).getResultList();
        return users;
    }
}
