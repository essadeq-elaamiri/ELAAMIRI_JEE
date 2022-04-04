package com.elaamiri.patientsmanagement.security.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(unique = true)
    private String roleName;
    private String roleDescription;

}
