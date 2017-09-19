package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Lenovo on 13.09.2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String title;

    @Column(name = "DESCRIPTION")
    private String content;

}
