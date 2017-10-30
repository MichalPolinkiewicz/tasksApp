package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by Lenovo on 26.10.2017.
 */
@Getter
@AllArgsConstructor
public class TrelloBoard {

    private String id;
    private String name;
    private List<TrelloList> lists;
}
