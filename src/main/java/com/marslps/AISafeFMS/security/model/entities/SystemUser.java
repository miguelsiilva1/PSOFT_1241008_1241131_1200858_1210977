package com.marslps.AISafeFMS.security.model.entities;

import com.marslps.AISafeFMS.security.model.enums.SystemUserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class SystemUser implements UserDetails {
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SystemUser airport)) {return false;}
        return Objects.equals(this.username, airport.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public @Nullable String getPassword() {
        return this.password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.type.toString()));
    }


}
