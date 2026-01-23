package com.ahmadmalik.mySpringBootProject.repository;

import com.ahmadmalik.mySpringBootProject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.ObjectInput;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
