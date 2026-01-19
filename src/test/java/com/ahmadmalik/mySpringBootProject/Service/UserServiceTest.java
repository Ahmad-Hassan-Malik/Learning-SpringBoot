package com.ahmadmalik.mySpringBootProject.Service;

import com.ahmadmalik.mySpringBootProject.entity.Users;
import com.ahmadmalik.mySpringBootProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Test
    public void addTest() {
        assertEquals(4, 2+2);
    }

    @Test
    public void gettAlluserTest() {
        assertNotNull(userRepo.findAll());
    }

    @Test
    public void findByUsername() {
        Users user = userRepo.findByUserName("mian");
        System.out.println("ENTRIES FOR mian: " + user.getJournalEntries());
        assertTrue(!user.getJournalEntries().isEmpty());

    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("\nStarting new Test");
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "4,6,11",
            "55,1,56"
    })
    public void test(int a, int b, int expected) {
            assertEquals(expected,a+b);
    }


    @ParameterizedTest
    @CsvSource({
            "sufiiii",
            "mian",
            "Akram"
    })
    public void findByUserNameTest(String userName) {
        Users user = userRepo.findByUserName(userName);
        assertTrue(userRepo.existsById(user.getId()));
    }


}
