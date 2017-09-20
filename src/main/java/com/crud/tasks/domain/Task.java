package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Id;

/**
 * Created by Lenovo on 13.09.2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

}
