package com.unauthdeliveries.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_id", nullable = false)
    private Long id;
    private String application;
    private String appAccountName;
    private boolean isActive;
    private String jobTitle;
    private String department;
}
