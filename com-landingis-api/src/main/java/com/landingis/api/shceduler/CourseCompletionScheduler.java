package com.landingis.api.shceduler;

import com.landingis.api.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CourseCompletionScheduler {

    @Autowired
    private UserCourseService userCourseService;

    @Scheduled(fixedRate = 5000)
    public void runCourseCompletionCheck() {
        System.out.println("Checking course completion status ...");
        userCourseService.checkAndUpdateCourseCompletion();
    }
}
