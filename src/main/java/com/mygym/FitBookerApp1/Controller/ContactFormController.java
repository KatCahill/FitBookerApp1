package com.mygym.FitBookerApp1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")

public class ContactFormController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public String sendContactEmail(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        try {
            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("FitBookerApp@gmail.com"); // Sender's email (your Gmail)
            mailMessage.setTo("FitBookerApp@gmail.com"); // Recipient's email (you will always receive the email)
            mailMessage.setSubject("New Contact Message from " + name);
            mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);
            // Send the email
            mailSender.send(mailMessage);
            return "Message sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send message. Please try again.";
        }
    }    
}
