package com.crud.tasks.domain;

/**
 * Created by Lenovo on 24.10.2017.
 */
public class Mail {
    private String mailTo;
    private String toCC;
    private String subject;
    private String message;

    public Mail(String mailTo, String toCC,String subject, String message) {
        this.toCC = toCC;
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCC() {
        return toCC;
    }
}
