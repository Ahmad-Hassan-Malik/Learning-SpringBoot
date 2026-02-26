package com.ahmadmalik.mySpringBootProject.controller;

import com.ahmadmalik.mySpringBootProject.entity.JournalEntry;
import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.service.JournalEntryService;
import com.ahmadmalik.mySpringBootProject.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService jService;

    @Autowired
    private UserService userService;

//    @GetMapping("/abc/{userName}")
//    public ResponseEntity<?> getAllEntriesByUserName(@PathVariable String userName) {
//        Users user = userService.findByUserName(userName);
//        List<JournalEntry> all = user.getJournalEntries();
//        if (all != null && !all.isEmpty()) {
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
@GetMapping("/abc")
public ResponseEntity<?> getAllEntriesByUserName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    Users user = userService.findByUserName(userName);
    List<JournalEntry> all = user.getJournalEntries();
    if (all != null && !all.isEmpty()) {
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

//    @PostMapping("/xyz")
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {
//        myEntry.setDate(LocalDateTime.now());
//        jService.saveEntry(myEntry);
//        return true;
//    }

//    @PostMapping("/xyz/{userName}")
//    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
//        try {
//            myEntry.setDate(LocalDateTime.now());
//            jService.saveEntry(myEntry,userName);
//            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/xyz")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String userName = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            jService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryByID(@PathVariable ObjectId myId) {
//        return jService.getEntryByID(myId).orElse(null);
//    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users users = userService.findByUserName(userName);
        List<JournalEntry> allEntryOfUser = users.getJournalEntries();
        Optional<JournalEntry> entry = allEntryOfUser.stream().filter(x -> x.getId().equals(myId)).findFirst();
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @DeleteMapping("id/{myId}")
//    public boolean deleteJournalEntryByID(@PathVariable ObjectId myId) {
//        jService.deleteByID( myId);
//        return true;
//    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryByID(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users users = userService.findByUserName(userName);
        List<JournalEntry> allEntryOfUser = users.getJournalEntries();
        Optional<JournalEntry> entry = allEntryOfUser.stream().filter(x -> x.getId().equals(myId)).findFirst();
        if (entry.isPresent()) {
            jService.deleteByID(myId,userName);
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("id/{id}")
//    public boolean updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
//        JournalEntry old = jService.getEntryByID(id).orElse(null);
//        if (old != null) {
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
//            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
//        }
//        jService.saveEntry(old);
//        return true;
//    }
@PutMapping("id/{id}")
public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    Users users = userService.findByUserName(userName);
    List<JournalEntry> allEntryOfUser = users.getJournalEntries();
    Optional<JournalEntry> old = allEntryOfUser.stream().filter(x -> x.getId().equals(id)).findFirst();
    if (old.isPresent()) {
        old.get().setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.get().getTitle());
        old.get().setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.get().getContent());
//        old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
//        old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        jService.saveEntry(old.get());
        return new ResponseEntity<>(old.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    JournalEntry old = jService.getEntryByID(id).orElse(null);
//    if (old != null) {
//        old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
//        old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
//        jService.saveEntry(old);
//        return new ResponseEntity<>(old, HttpStatus.OK);
//    }
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
