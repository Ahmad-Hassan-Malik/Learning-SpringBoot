package com.ahmadmalik.mySpringBootProject.repository;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<Users> getUsersByUsernameAndRole(String userName, String Role);

  //  List<Users> getUsersbyRole(String role);
}
