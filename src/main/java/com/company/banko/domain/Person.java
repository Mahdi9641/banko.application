package com.company.banko.domain;

import com.company.banko.CustomAnnotation.NationalNumberValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Person_sequence")
@Table(name = "Person", uniqueConstraints = {@UniqueConstraint(columnNames = {"nationalNumber"}, name = "national_Number_UNIQUE")})
public class Person extends AbstractPersistableCustom implements Serializable {

    @Column(name = "firstName", length = 100 , nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "lastName", length = 100, nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(name = "age", nullable = false)
    private long age;

    @Column(name = "nationalNumber", nullable = false)
    private String nationalNumber;

    @Column(name = "birthDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<FinancialAccount> financialAccounts;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("nationalNumber=" + nationalNumber)
                .add("birthDate=" + birthDate)
                .toString();
    }
}
