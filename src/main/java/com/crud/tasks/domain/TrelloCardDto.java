package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Lenovo on 11.10.2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;
}
