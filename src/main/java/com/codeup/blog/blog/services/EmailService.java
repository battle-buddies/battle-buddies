//package com.codeup.blog.blog.services;
//
//import com.codeup.blog.blog.models.Meetup;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service("mailService")
//public class EmailService {
//
//    @Autowired
//    public JavaMailSender email;
//
//    @Value("${spring.mail.from}")
//    private String from;
//
//    public void prepareAndSend(Meetup post, String subject, String body) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(from);
//        msg.setTo(post.getUser().getEmail());
//        msg.setSubject(subject);
//        msg.setText(body);
//
//        try{
//            this.email.send(msg);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
//}
