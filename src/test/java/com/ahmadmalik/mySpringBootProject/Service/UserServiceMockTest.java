package com.ahmadmalik.mySpringBootProject.Service;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.repository.UserRepository;
import com.ahmadmalik.mySpringBootProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)     //   Turn on mockito. by this @Mocks are initialized. otherwise null pointer exception
public class UserServiceMockTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepo;

    @Test
    public void testUserExistOrNot() {
        Users dummyUser = new Users();
        dummyUser.setUserName("Ahmad");
        dummyUser.setPassword("123wedkd");

        when(userRepo.findByUserName("Ahmad")).thenReturn(dummyUser);

        Users result = userService.findByUserName("Ahmad");

        assertNotNull(result);
        assertEquals("Ahmad", result.getUserName());
    }
}
