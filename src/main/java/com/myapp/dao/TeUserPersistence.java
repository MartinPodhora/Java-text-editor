package com.myapp.dao;

import com.myapp.TEException.TEException;
import com.myapp.model.TeUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeUserPersistence {
    @PersistenceContext(unitName = "TextEditor")
    private EntityManager entityManager;

    public List<TeUser> retrieveAllUsers() throws TEException {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeUser> query = cb.createQuery(TeUser.class);
        Root<TeUser> root = query.from(TeUser.class);

        query.select(root);

        List<TeUser> users = this.entityManager.createQuery(query).getResultList();

        if (users.isEmpty()) {
            throw new TEException("There are no users in application.");
        }

        return users;
    }

    public TeUser findUser(String username) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, username);

        if (user == null) {
            throw new TEException("User with specified username does not exists");
        }

        return user;
    }

    public TeUser persistUser(TeUser paUser) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, paUser.getUsername());

        if (user != null) {
            throw new TEException("User with specified username already exists.");
        }

        this.entityManager.persist(paUser);
        return paUser;
    }

    public TeUser updateUser(TeUser paUser) throws TEException {
        TeUser user = this.findUser(paUser.getUsername());
        this.entityManager.merge(user);
        return paUser;
    }

    public String deleteUser(String username) throws TEException {
        TeUser user = this.findUser(username);
        this.entityManager.remove(user);
        return username;
    }

    public TeUser logIn(TeUser paUser) throws TEException {
        TeUser user = this.findUser(paUser.getUsername());
        if (!user.getPassword().equals(paUser.getPassword())) {
            throw new TEException("Wrong password.");
        }
        return user;
    }
}
