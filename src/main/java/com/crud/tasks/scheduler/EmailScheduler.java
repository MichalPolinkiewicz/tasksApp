package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 25.10.2017.
 */
@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    private static final String SUBJECT = "Tasks : once a day e-mail";


    @Scheduled (cron = "0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        String taskVal = " tasks";

        if (size == 1) taskVal = " task";

        simpleEmailService.sendDailyMail(
                new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                        "Currently, in database you got: " + size + taskVal));
    }
}
