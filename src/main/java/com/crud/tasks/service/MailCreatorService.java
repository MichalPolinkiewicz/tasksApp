package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.info.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Lenovo on 24.11.2017.
 */
@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyInfo companyInfo;

    public String buildTrelloCardEmail (String message){
        Context context = new Context();

        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_crud/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company", companyInfo.toString());
        return templateEngine.process("D:\\tasks\\src\\main\\resources\\templates\\mail\\created_trello_card_mail.html" , context);
    }

    public String buildDailyEmail(String msg){
        Context context = new Context();

        context.setVariable("message", msg);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_crud/");
        context.setVariable("button", "Show tasks");
        context.setVariable("company", companyInfo.toString());
        return templateEngine.process("D:\\tasks\\src\\main\\resources\\templates\\mail\\daily_mail.html" , context);

    }
}

