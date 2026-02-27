package com.ahmadmalik.mySpringBootProject.controller;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.service.UserDetailServiceimpl;
import com.ahmadmalik.mySpringBootProject.service.UserService;
import com.ahmadmalik.mySpringBootProject.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
@Tag(name = "Public APIs")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceimpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;




    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }




    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody Users newUser) {
        try {
            userService.saveNewUser(newUser);
            log.info("User created {}", newUser.getUserName());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("User {} not created", newUser.getUserName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception While logging in");
            return new ResponseEntity<>("Incorrect userName aPassword" , HttpStatus.BAD_REQUEST);
        }
    }

}
