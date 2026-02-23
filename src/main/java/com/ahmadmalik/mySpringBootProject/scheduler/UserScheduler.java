package com.ahmadmalik.mySpringBootProject.scheduler;

import com.ahmadmalik.mySpringBootProject.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;


  //  @Scheduled(cron = "*/5 * * * * *")
//    public void sendEmailToHaji() {
//        emailService.sendEmail("sp24-bse-029@cuilahore.edu.pk","FBI INVESTIGATION", "hi moli ammar kaise ho");
//        log.info("message sent");
//    }
}
