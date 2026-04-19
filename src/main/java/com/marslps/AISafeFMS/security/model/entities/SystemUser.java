package com.marslps.AISafeFMS.security.model.entities;

import com.marslps.AISafeFMS.security.model.enums.SystemUserType;
import jakarta.persistence.*;

@Entity
public class SystemUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sysuser_id;
    @Column
    private String username;
    @Column
    private String password;
    @Enumerated(value = EnumType.STRING)
    private SystemUserType type;

    public SystemUser() {}

    public SystemUser(String username, String password, SystemUserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
