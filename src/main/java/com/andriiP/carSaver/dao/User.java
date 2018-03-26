package com.andriiP.carSaver.dao;

import com.andriiP.carSaver.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
@ToString(exclude = "password")
public class User extends AbstractEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    private String[] roles;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    protected User() {
    }

    public User(String userName, String password, String email, String... roles) {
        this.userName = userName;
        setPassword(password);
        this.email = email;
        this.roles = roles;
    }
}
