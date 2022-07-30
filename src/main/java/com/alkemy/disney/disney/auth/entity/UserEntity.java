package com.alkemy.disney.disney.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UserEntity  { //implements UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    public UserEntity() {
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.enabled=true;
    }

}