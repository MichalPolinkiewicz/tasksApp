package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Lenovo on 27.10.2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;
}
