package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Lenovo on 13.09.2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private long id;
    private String title;
    private String content;
}
