package com.ahmadmalik.mySpringBootProject.repository;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Users> getUsersByUsernameAndRole(String userName, String role) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userName").regex(userName, "i"));
        query.addCriteria(Criteria.where("roles").is(role));
        return mongoTemplate.find(query, Users.class);
    }

//    @Override
//    public List<Users> getUsersbyRole(String role) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("roles").is(role));
//        return mongoTemplate.find(query, Users.class);
//    }

}
