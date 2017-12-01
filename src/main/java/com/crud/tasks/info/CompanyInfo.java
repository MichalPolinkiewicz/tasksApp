package com.crud.tasks.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 29.11.2017.
 */
@Component
@Getter
public class CompanyInfo {

    @Value("${info.app.name}")
    private String name;

    @Value("${info.app.description}")
    private String description;

    @Value("${info.app.version}")
    private String version;

    @Value("${info.administrator.email}")
    private String adminEmail;

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                '}';
    }
}
