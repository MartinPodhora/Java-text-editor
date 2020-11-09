package com.myapp.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TE_USER")
public class TeUser {

    @Id
    @NotNull(message = "Username cannot be null")
    @Column(name = "USERNAME", updatable = false, nullable = false, unique = true)
    @Size(min = 1, max = 50)
    private String username;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull(message = "fist name at text editor user cannot be null")
    @Size(min = 1, max = 20)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull(message = "last name at text editor user cannot be null")
    @Size(min = 1, max = 20)
    private String lastName;

    @Column(name = "USER_PASSWORD", nullable = false)
    @NotNull(message = "password at text editor user cannot be null")
    @Size(min = 1, max = 50)
    private String password;

    @Column(name = "EMAIL")
    @Size(min = 1, max = 50)
    private String email;

    @Column(name = "PHOTO")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TeDocument> documents;

    public TeUser() {

    }

    public TeUser(String username, String firstName, String lastName, String password, String email, String photo, List<TeDocument> documents) {
        this.setUsername(username);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoto(photo);
        this.setDocuments(documents);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<TeDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<TeDocument> documents) {
        this.documents = documents;
    }
}
