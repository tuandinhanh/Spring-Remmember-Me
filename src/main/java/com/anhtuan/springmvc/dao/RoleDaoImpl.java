package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
    @Override
    public Role findById(int id) {
        return getByKey(id);
    }

    @Override
    public Role findByType(String type) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("type"), type));
        try {
            return (Role) getSession().createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> findAllRoles() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(roleRoot.get("type")));
        return (List<Role>) getSession().createQuery(criteriaQuery).getResultList();
    }
}
