package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Lenovo on 24.10.2017.
 */
@Service
public class SimpleEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(Mail mail) {

        LOGGER.info("Starting e-mail preparation...");

        try {
            javaMailSender.send(createEmailMessage(mail));
            LOGGER.info("E-mail to " + mail.getMailTo() + " has been sent");

            boolean isEmpty = mail.getToCC() == null;

            if (!isEmpty) {
                String cc = mail.getToCC();
                LOGGER.info("Copy sent to " + cc);
            }

        } catch (MailException exception) {
            LOGGER.error("Failed to precess e-mail sendind", exception.getMessage(), exception);

        }
    }

    private SimpleMailMessage createEmailMessage(Mail mail) {

        boolean ccIsEmpty = mail.getToCC() == null;

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        if (!ccIsEmpty) mailMessage.setCc(mail.getToCC());

        return mailMessage;

    }

}
