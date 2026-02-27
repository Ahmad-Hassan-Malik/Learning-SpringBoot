package com.ahmadmalik.mySpringBootProject.controller;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<Users> allUser = userService.getAllUsers();
        if (allUser != null && !allUser.isEmpty()) {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody Users user) {
        return new ResponseEntity<>(userService.saveAdmin(user), HttpStatus.CREATED);
    }


}
