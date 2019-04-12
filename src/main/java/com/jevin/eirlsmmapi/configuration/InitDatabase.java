package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.form.UserSignUp;
import com.jevin.eirlsmmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitDatabase implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) {
        init();
    }

    public void init() {

        Set<String> adminRole = new HashSet<>();
        adminRole.add("admin");

        Set<String> mmRole = new HashSet<>();
        mmRole.add("mm");

        Set<String> mcRole = new HashSet<>();
        mcRole.add("mc");

        Set<String> supplierRole = new HashSet<>();
        supplierRole.add("supplier");

        Set<String> externalRole = new HashSet<>();
        externalRole.add("external");

        UserSignUp admin = new UserSignUp("Admin", "admin", "admin@gmail.com", adminRole, "qwerty");
        UserSignUp mm = new UserSignUp("Tom", "tom", "tom@gmail.com", mmRole, "qwerty");
        UserSignUp mc = new UserSignUp("John", "john", "john@gmail.com", mcRole, "qwerty");
        UserSignUp supplier = new UserSignUp("Supplier", "supplier", "supplier@gmail.com", supplierRole, "qwerty");
        UserSignUp external = new UserSignUp("External", "external", "external@gmail.com", externalRole, "qwerty");

        admin.setRole(adminRole);
        mm.setRole(mmRole);
        mc.setRole(mcRole);
        supplier.setRole(supplierRole);
        external.setRole(externalRole);

        userService.createUser(admin);
        userService.createUser(mm);
        userService.createUser(mc);
        userService.createUser(supplier);
        userService.createUser(external);
    }


}
