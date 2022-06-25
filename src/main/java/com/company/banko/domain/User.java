package com.company.banko.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Getter
@Setter
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "User_sequence")
@Entity
@Table(name = "app_user")
public class User extends AbstractPersistableCustom implements Serializable {


    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .add("role='" + role + "'")
                .toString();
    }
}