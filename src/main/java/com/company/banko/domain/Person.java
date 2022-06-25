package com.company.banko.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middlename", length = 50, nullable = false)
    private String middlename;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private long age;

    @Column(name = "mobile_no", length = 50, unique = true)
    private String mobileNo;

    @Column(name = "email_address", length = 50, unique = true)
    private String emailAddress;

    @Column(name = "nationalNumber", nullable = false)
    private String nationalNumber;

    @Column(name = "birthDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<FinancialAccount> financialAccounts;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("middlename='" + middlename + "'")
                .add("lastName='" + lastName + "'")
                .add("age=" + age)
                .add("mobileNo='" + mobileNo + "'")
                .add("emailAddress='" + emailAddress + "'")
                .add("nationalNumber='" + nationalNumber + "'")
                .add("birthDate=" + birthDate)
                .add("financialAccounts=" + financialAccounts)
                .add("transactions=" + transactions + "'")
                .add("office='" + office)
                .toString();
    }
}
