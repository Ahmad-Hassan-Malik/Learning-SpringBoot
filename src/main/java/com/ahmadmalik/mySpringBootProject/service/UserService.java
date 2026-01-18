package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public Users saveAdmin(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        return userRepository.save(user);
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUsersByID(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteUserByID(ObjectId id) {
        userRepository.deleteById(id);
    }

    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
