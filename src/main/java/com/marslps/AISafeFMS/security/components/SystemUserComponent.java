package com.marslps.AISafeFMS.security.components;

import com.marslps.AISafeFMS.security.model.entities.SystemUser;
import com.marslps.AISafeFMS.security.model.enums.SystemUserType;
import com.marslps.AISafeFMS.security.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SystemUserComponent implements CommandLineRunner {
    @Autowired
    private SystemUserRepository sysuser_repo;
    @Autowired
    private PasswordEncoder password_encoder;


    @Override
    public void run(String... args) throws Exception {
        sysuser_repo.save(new SystemUser("atcc", password_encoder.encode("1234"), SystemUserType.ATCC));
        sysuser_repo.save(new SystemUser("backoffice", password_encoder.encode("1234"), SystemUserType.BACKOFFICE_OP));
        sysuser_repo.save(new SystemUser("maintenance_tech", password_encoder.encode("1234"), SystemUserType.MAINTENANCE_TECH));
        sysuser_repo.save(new SystemUser("maintenance_super", password_encoder.encode("1234"), SystemUserType.MAINTENANCE_SUPER));
        sysuser_repo.save(new SystemUser("sys_admin", password_encoder.encode("1234"), SystemUserType.SYSTEM_ADMIN));
    }
}
