package com.myapp.dao;

import com.myapp.TEException.TEException;
import com.myapp.model.TeDocument;
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

    public TeUser deleteUser(String username) throws TEException {
        TeUser user = this.findUser(username);

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeDocument> query = cb.createQuery(TeDocument.class);
        Root<TeDocument> root = query.from(TeDocument.class);

        query.select(root).where(cb.equal(root.get("user"), user));

        List<TeDocument> docs = this.entityManager.createQuery(query).getResultList();

        if (!docs.isEmpty()) {
            for (TeDocument doc : docs) {
                this.entityManager.remove(doc);
            }
        }

        this.entityManager.remove(user);
        return user;
    }

    public TeUser logIn(String username, String password) throws TEException {
        TeUser user = this.findUser(username);

        if (!user.getPassword().equals(password)) {
            throw new TEException("Wrong password.");
        }

        return user;
    }

    public String getPhoto(String username) throws TEException {
        TeUser user = this.findUser(username);

        return user.getPhoto();
    }

    public TeUser updatePhoto(String photo, String username) throws TEException {
        TeUser user = this.findUser(username);

        user.setPhoto(photo);
        this.entityManager.merge(user);

        return user;
    }
}
