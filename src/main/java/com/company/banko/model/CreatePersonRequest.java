package com.company.banko.model;

import com.company.banko.CustomAnnotation.NationalNumberValidation;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("CreatePersonRequest")
public class CreatePersonRequest {


    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private long age;

    @NotNull
    @NationalNumberValidation
    private String nationalNumber;

    @NotNull
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
