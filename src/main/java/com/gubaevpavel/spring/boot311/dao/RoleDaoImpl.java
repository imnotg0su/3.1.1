package com.gubaevpavel.spring.boot311.dao;


import com.gubaevpavel.spring.boot311.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public HashSet<Role> getRoleSet(String[] s) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : s) {
            roleSet.add(getRoleByName(role));
        }
        return (HashSet) roleSet;
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select r from Role r where r.name=:role",
                Role.class).setParameter("role", name).getSingleResult();
    }
}
