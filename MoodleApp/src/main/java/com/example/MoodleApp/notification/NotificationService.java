package com.example.MoodleApp.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private static final String FAST_API_URL = "http://localhost:8000/api/academia";

    public void notifyProfessorForCreate() {
        String createUri = FAST_API_URL + "/";
        logger.info("Professor is notified: You can add materials to the lecture at: {}", createUri);
    }

    public void notifyProfessorForUpdate(String lectureCode) {
        String updateUri = FAST_API_URL + "/disciplines/" + lectureCode;
        logger.info("Professor is notified: You can update materials for lecture {} at : {}", lectureCode, updateUri);
    }
}
