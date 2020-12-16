package com.myapp.dao;

import com.myapp.TEException.TEException;
import com.myapp.model.TeDocument;
import com.myapp.model.TeStyle;
import com.myapp.model.TeUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

    public TeUser createUser(TeUser paUser) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, paUser.getUsername());
        TeStyle style = this.entityManager.find(TeStyle.class, 1L);

        if (user != null) {
            throw new TEException("User with specified username already exists.");
        }

        if (paUser.getPassword() == null) {
            paUser.setPassword(paUser.getUsername());
        }

        String newPsw = BCrypt.hashpw(paUser.getPassword(), BCrypt.gensalt(12));
        paUser.setPassword(newPsw);
        paUser.setStyle(style);

        this.entityManager.persist(paUser);
        return paUser;
    }

    public TeUser updateUser(TeUser paUser) throws TEException {
        TeUser user = this.findUser(paUser.getUsername());
        user.setFirstName(paUser.getFirstName());
        user.setLastName(paUser.getLastName());
        user.setEmail(paUser.getEmail());

        this.entityManager.merge(user);
        return user;
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

        if (!BCrypt.checkpw(password, user.getPassword())) {
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

    public TeUser updateStyle(TeStyle style, String username) throws TEException {
        TeUser user = this.findUser(username);

        user.setStyle(style);
        this.entityManager.merge(user);

        return user;
    }

    public TeUser changePassword(String passwords, String username) throws TEException {
        TeUser user = this.findUser(username);
        String[] passwds = passwords.split("," , 2);

        if (!BCrypt.checkpw(passwds[0], user.getPassword())) {
            throw new TEException("Wrong password");
        }

        if (passwds[1].equals(passwds[0])) {
            throw new TEException("New password cant be same as old one");
        }

        String newPsw = BCrypt.hashpw(passwds[1], BCrypt.gensalt(12));
        user.setPassword(newPsw);
        this.entityManager.merge(user);

        return user;
    }
}
