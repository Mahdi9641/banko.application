package com.company.banko.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private long age;

    private long nationalNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "person" , fetch = FetchType.LAZY)
    private Set<FinancialAccount> financialAccounts;


    public Person() {
    }

    public Person(long id, String firstName, String lastName, long nationalNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalNumber = nationalNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public Set<FinancialAccount> getFinancialAccounts() {
        return financialAccounts;
    }

    public void setFinancialAccounts(Set<FinancialAccount> financialAccounts) {
        this.financialAccounts = financialAccounts;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

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
