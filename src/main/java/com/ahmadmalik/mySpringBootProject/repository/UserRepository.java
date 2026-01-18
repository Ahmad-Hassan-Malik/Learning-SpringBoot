package com.ahmadmalik.mySpringBootProject.repository;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String userName);

    void deleteByUserName(String name);
}
