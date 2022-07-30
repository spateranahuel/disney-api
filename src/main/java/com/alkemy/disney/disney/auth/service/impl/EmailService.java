package com.alkemy.disney.disney.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String mail) {
        SimpleMailMessage MailMessage = new SimpleMailMessage();
        MailMessage.setTo(mail);
        MailMessage.setSubject("Bienvenido");
        MailMessage.setText("Su cuenta a sido creada con exito");
        mailSender.send(MailMessage);

    }


}
