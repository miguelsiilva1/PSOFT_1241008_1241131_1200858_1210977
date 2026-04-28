package com.marslps.AISafeFMS.security.model.entities;

import com.marslps.AISafeFMS.security.model.enums.SystemUserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class SystemUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sysuser_id;
    @Column @NotBlank
    private String username;
    @Column @NotBlank
    private String password;
    @Enumerated(value = EnumType.STRING)
    private SystemUserType type;

    public SystemUser() {
        this.username = "something";
        this.password = "something";
        this.type = SystemUserType.SYSTEM_ADMIN;
    }

    public SystemUser(@NotBlank String username, @NotBlank String password, SystemUserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
