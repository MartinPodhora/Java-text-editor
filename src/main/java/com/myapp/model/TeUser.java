package com.myapp.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @JsonbTransient
    @Column(name = "USER_PASSWORD", nullable = false)
    @NotNull(message = "password at text editor user cannot be null")
    private String password;

    @Column(name = "EMAIL")
    @Size(min = 1, max = 50)
    private String email;

    @JsonbTransient
    @Column(name = "PHOTO")
    private String photo;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "style at text editor user cannot be null")
    @JoinColumn(name = "TE_STYLE_ID", referencedColumnName = "ID")
    private TeStyle style;

    public TeUser() {

    }

    public TeStyle getStyle() {
        return style;
    }

    public void setStyle(TeStyle style) {
        this.style = style;
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
}
