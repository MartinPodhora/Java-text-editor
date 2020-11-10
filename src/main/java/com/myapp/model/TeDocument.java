package com.myapp.model;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "TE_DOCUMENT")
public class TeDocument {

    @Id
    @NotNull(message = "Text editor document id cannot be null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private long id;

    @Column(name = "NAME", nullable = false)
    @NotNull(message = "NAME at text editor document cannot be null")
    @Size(min = 1, max = 50)
    private String name;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Text editor document Created date cannot be null")
    @Column(name = "DATE_CREATED", nullable = false)
    private Date dateCreated;

    @Column(name = "DOC_CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TE_USER_USERNAME", referencedColumnName = "USERNAME")
    private TeUser user;

    public TeDocument() {
    }

    public TeDocument(long id, String name, Date dateCreated, String content, TeUser user) {
        this.setId(id);
        this.setName(name);
        this.setDateCreated(dateCreated);
        this.setContent(content);
        this.setUser(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TeUser getUser() {
        return user;
    }

    public void setUser(TeUser user) {
        this.user = user;
    }
}
