package com.company.banko.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("CreatePersonRequest")
public class CreatePersonRequest {


    private long id;

    private String firstName;

    private String lastName;

    private long age;

    private long nationalNumber;

    private Date birthDate;

    @Override
    public String toString() {
        return new StringJoiner(", ", CreatePersonRequest.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age=" + age)
                .add("nationalNumber=" + nationalNumber)
                .add("birthDate=" + birthDate)
                .toString();
    }
}
