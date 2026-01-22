package com.ahmadmalik.mySpringBootProject.controller;


import com.ahmadmalik.mySpringBootProject.api_Response.WeatherResponse;
import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.repository.UserRepository;
import com.ahmadmalik.mySpringBootProject.service.ChatgptService;
import com.ahmadmalik.mySpringBootProject.service.CohereService;
import com.ahmadmalik.mySpringBootProject.service.UserService;
import com.ahmadmalik.mySpringBootProject.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ChatgptService chatgptService;

    @Autowired CohereService cohereService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/abc")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("id/{userName}")
    public ResponseEntity<Users> getUserByID(@PathVariable String userName) {
        Users user = userService.findByUserName(userName);
        if (user != null && !user.equals("")) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping()
//    public ResponseEntity<?> createUser(@RequestBody Users newUser) {
//        try {
//            userService.saveUser(newUser);
//            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id) {
//        if (userService.getUsersByID(id).isPresent()) {
//            userService.deleteUserByID(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @PutMapping("put/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody Users updated, @PathVariable String userName) {
//        Users old = userService.findByUserName(userName);
//        if (old != null) {
//            old.setUserName(updated.getUserName() != null && !updated.getUserName().equals("") ? updated.getUserName() : old.getUserName());
//            old.setPassword(updated.getPassword() != null && !updated.getPassword().equals("") ? updated.getPassword() : old.getPassword());
//            userService.saveUser(old);
//            return new ResponseEntity<>(old, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users updated) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users old = userService.findByUserName(userName);
            old.setUserName(updated.getUserName() != null && !updated.getUserName().equals("") ? updated.getUserName() : old.getUserName());
            old.setPassword(updated.getPassword() != null && !updated.getPassword().equals("") ? updated.getPassword() : old.getPassword());
            userService.saveUser(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
     //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> weatherCondition() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Multan");
        WeatherResponse.CurrentCondition current = weatherResponse.getData().getCurrentCondition().get(0);
        if (current != null) {
            System.out.println("hi " + authentication.getName());
            return new ResponseEntity<>(current, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    //  code is ok. but the problem is money. no balance in gpt account. that is y not running
    @PostMapping("gpt")
    public ResponseEntity<?> chatgptUsingAPI(@RequestBody String question) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Hi " + authentication.getName());
        String response = chatgptService.getChatgptReply(question);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("cohere")
    public ResponseEntity<?> cohereUsingAPI(@RequestBody Map<String, String> payload) {
        String question = payload.get("message");
        System.out.println("DEBUG QUESTION: " + question);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Hi " + authentication.getName());
        String response = cohereService.getCohereReply(question);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
