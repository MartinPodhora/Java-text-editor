package com.myapp.dao;

import com.myapp.TEException.TEException;
import com.myapp.model.TeStyle;
import com.myapp.model.TeUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeRefData {
    @PersistenceContext(unitName = "TextEditor")
    private EntityManager entityManager;

    public List<TeStyle> retrieveAllStyles() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeStyle> query = cb.createQuery(TeStyle.class);
        Root<TeStyle> root = query.from(TeStyle.class);

        query.select(root);

        List<TeStyle> styles = this.entityManager.createQuery(query).getResultList();

        return styles;
    }

    public TeStyle findStyleForUser(String username) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, username);

        if (user == null) {
            throw new TEException("User with specified username does not exists");
        }

        return user.getStyle();
    }

    public TeStyle findStyle(long id) throws TEException {
        TeStyle style = this.entityManager.find(TeStyle.class, id);

        if (style == null) {
            throw new TEException("Cannot find type with entered id.");
        }

        return style;
    }

    public TeStyle updateStyle(TeStyle style) throws TEException {
        style.validate();
        this.entityManager.merge(style);
        return findStyle(style.getId());
    }

    public TeStyle createStyle(TeStyle style) throws TEException {
        style.validate();
        this.entityManager.persist(style);
        return style;
    }

    public Long deleteStyle(Long id) throws TEException {
        TeStyle style = this.findStyle(id);
        TeStyle basic = this.findStyle(1);

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeUser> query = cb.createQuery(TeUser.class);
        Root<TeUser> root = query.from(TeUser.class);

        query.select(root).where(cb.equal(root.get("style"), style));

        List<TeUser> users = this.entityManager.createQuery(query).getResultList();

        if (!users.isEmpty()) {
            for (TeUser tmp : users) {
                tmp.setStyle(basic);
                this.entityManager.merge(tmp);
            }
        }

        this.entityManager.remove(style);
        return id;
    }
}
