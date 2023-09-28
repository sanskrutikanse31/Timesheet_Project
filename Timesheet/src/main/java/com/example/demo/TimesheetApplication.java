package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

//import com.example.demo.EmailService.EmailServices;

@SpringBootApplication
public class TimesheetApplication {
	
//	@Autowired
//	private EmailServices emailservices;

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//    public void sendEmailOnStartup() {
//        String to = "recipient@example.com";
//        String subject = "Hello from Spring Boot!";
//        String body = "This is an automated email sent from Spring Boot.";
//        emailservices.sendEmail(to, subject, body);
//    }
	

}
