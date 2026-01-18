package com.ahmadmalik.mySpringBootProject.controller;

import com.ahmadmalik.mySpringBootProject.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

//    private Map<String, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping("/abc")
//    public List<JournalEntry> getAll() {
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping("/xyz")
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {
//        journalEntries.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryByID(@PathVariable Long myId) {
//        return journalEntries.get(myId);
//    }
//
//
//    @DeleteMapping("id/{myId}")
//    public boolean deleteJournalEntryByID(@PathVariable Long myId) {
//        journalEntries.remove(myId);
//        return true;
//    }
//
//    @PutMapping("id/{id}")
//    public boolean updateJournalEntry(@PathVariable String id, @RequestBody JournalEntry myEntry) {
//        journalEntries.put(id,myEntry);
//        return true;
//    }
}
