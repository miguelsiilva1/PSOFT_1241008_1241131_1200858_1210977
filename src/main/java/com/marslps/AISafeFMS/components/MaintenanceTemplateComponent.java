package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import com.marslps.AISafeFMS.model.entities.NumberedItem;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import com.marslps.AISafeFMS.repository.MaintenanceTemplateRepository;
import com.sun.tools.javac.Main;
import org.apache.axis.types.PositiveInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class MaintenanceTemplateComponent implements CommandLineRunner {
    @Autowired
    private MaintenanceTemplateRepository maintenance_template_repo;

    @Override
    public void run(String... args) throws Exception {
        maintenance_template_repo.save(
                new MaintenanceTemplate(
                        MaintenanceTemplateType.OVERHAUL,
                        new NonEmptyString("New Paint"),
                        new HashSet<>(Arrays.asList(
                                new NonEmptyString("1"),
                                new NonEmptyString("2"),
                                new NonEmptyString("3")
                        )),
                        new ArrayList<>(Arrays.asList(
                                new NumberedItem(
                                        new PositiveInteger("1"),
                                        new NonEmptyString("Paint")
                                ),
                                new NumberedItem(
                                        new PositiveInteger("2"),
                                        new NonEmptyString("Let it dry")
                                ),
                                new NumberedItem(
                                        new PositiveInteger("3"),
                                        new NonEmptyString("Re-paint")
                                )
                        )),
                        MaintenanceComponent.EXTERIOR,
                        new PositiveDouble(195000)
                ));

        maintenance_template_repo.save(
                new MaintenanceTemplate(
                        MaintenanceTemplateType.MODIFICATION,
                        new NonEmptyString("Change Engine"),
                        new HashSet<>(Arrays.asList(
                                new NonEmptyString("1"),
                                new NonEmptyString("2"),
                                new NonEmptyString("3")
                        )),
                        new ArrayList<>(Arrays.asList(
                                new NumberedItem(
                                        new PositiveInteger("1"),
                                        new NonEmptyString("Order an Engine")
                                ),
                                new NumberedItem(
                                        new PositiveInteger("2"),
                                        new NonEmptyString("Remove the current Engine")
                                ),
                                new NumberedItem(
                                        new PositiveInteger("3"),
                                        new NonEmptyString("Install the new engine")
                                )
                        )),
                        MaintenanceComponent.ENGINE,
                        new PositiveDouble(738000)
                ));
    }

}
