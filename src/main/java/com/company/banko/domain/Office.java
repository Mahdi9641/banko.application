package com.company.banko.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Office_sequence")
@Table(name = "mahdi_office", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}, name="name_UNIQUE")})
public class Office  extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "opening_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openingDate;

    @Column(name = "external_id", length = 100)
    private String externalId;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Office> children = new HashSet<>();

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id")
   @JsonIgnore
    private Office parent;

}
