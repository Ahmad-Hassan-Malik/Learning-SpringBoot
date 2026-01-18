package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.entity.JournalEntry;
import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Users user = userService.findByUserName(userName);
            JournalEntry saved = repo.save(entry);
            user.getJournalEntries().add(saved);
         //   user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {
            //   this line will give error and it will not roll back in case of transaction fails
           // e.printStackTrace();

            System.out.println("Error while saving entrity");
            throw new RuntimeException(e);
        }
    }

//    public void saveEntry(JournalEntry entry) {
//        repo.save(entry);
//    }



    public List<JournalEntry> getAllEntries() {
        return repo.findAll();
    }

   public Optional<JournalEntry> getEntryByID(ObjectId id) {
        return repo.findById(id);
   }

   public void deleteByID(ObjectId id, String userName) {
        try {
            Users user = userService.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            repo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }



}
