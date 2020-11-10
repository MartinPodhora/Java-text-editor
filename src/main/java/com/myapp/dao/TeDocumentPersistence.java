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

public class TeDocumentPersistence {
    @PersistenceContext(unitName = "TextEditor")
    private EntityManager entityManager;

    public TeDocument addDocument(TeDocument paDoc) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, paDoc.getUser().getUsername());

        if (user == null) {
            throw new TEException("User with specified username does not exists.");
        }

        TeDocument document = this.entityManager.find(TeDocument.class, paDoc.getId());

        if (document != null) {
           paDoc.setId(generateNewID());
        }

        paDoc.setUser(user);
        this.entityManager.persist(paDoc);
        return paDoc;
    }

    public List<TeDocument> getAll(String username) throws TEException {
        TeUser user = this.entityManager.find(TeUser.class, username);

        if (user == null) {
            throw new TEException("User with specified username does not exists");
        }

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeDocument> query = cb.createQuery(TeDocument.class);
        Root<TeDocument> root = query.from(TeDocument.class);

        query.select(root).where(cb.equal(root.get("user"), user));

        List<TeDocument> docs = this.entityManager.createQuery(query).getResultList();

        if (docs.isEmpty()) {
            throw new TEException("There are no documents for user: " + user.getUsername());
        }

        return docs;
    }

    public TeDocument updateDocument(TeDocument doc) throws TEException {
        TeDocument document = this.entityManager.find(TeDocument.class, doc.getId());

        if (document == null) {
            throw new TEException("Document does not exists.");
        }

        this.entityManager.merge(doc);
        return doc;
    }

    public Long deleteDocument(Long id) throws TEException {
        TeDocument doc = this.entityManager.find(TeDocument.class, id);

        if (doc == null) {
            throw new TEException("Document with specified username does not exists.");
        }

        this.entityManager.remove(doc);
        return id;
    }

    private Long generateNewID() {
        long max = 0;
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TeDocument> query = cb.createQuery(TeDocument.class);
        Root<TeDocument> root = query.from(TeDocument.class);
        query.select(root);

        List<TeDocument> docs = this.entityManager.createQuery(query).getResultList();

        for (TeDocument doc : docs) {
            if (doc.getId() > max) {
                max = doc.getId() + 1;
            }
        }

        return max;
    }
}
