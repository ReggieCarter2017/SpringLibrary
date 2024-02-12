package org.example.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.example.models.Role;
import org.example.models.User;
import org.example.repos.RoleRepo;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;


    public List<User> findAll() {
        return userRepo.findAll();
    }

    @PostConstruct
    public void createUsersAndRoles() {
        Faker faker = new Faker();

        User admin = new User(faker.name().name(), encoder.encode("admin123"));
        User user = new User(faker.name().name(), encoder.encode("user123"));

        Role adminRole = new Role("ROLE_ADMIN");
        Role readerRole = new Role("ROLE_READER");

        roleRepo.save(adminRole);
        roleRepo.save(readerRole);

        userRepo.save(admin);
        userRepo.save(user);
    }




}
