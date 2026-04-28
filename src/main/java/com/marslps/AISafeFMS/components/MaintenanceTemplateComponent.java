package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.entities.MaintenancePart;
import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import com.marslps.AISafeFMS.model.vo.*;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.repository.MaintenanceTemplateRepository;
import org.apache.axis.types.PositiveInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class MaintenanceTemplateComponent implements CommandLineRunner {
    @Autowired
    private MaintenanceTemplateRepository maintenance_template_repo;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource("maintenance_templates.json").getInputStream();
        List<MaintenanceTemplate> types = mapper.readValue(file, new TypeReference<>() {});
        maintenance_template_repo.saveAll(types);
    }
}
