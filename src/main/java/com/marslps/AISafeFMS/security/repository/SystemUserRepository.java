package com.marslps.AISafeFMS.security.repository;

import com.marslps.AISafeFMS.security.model.entities.SystemUser;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {
    public Optional<SystemUser> findByUsername(String username);
}
