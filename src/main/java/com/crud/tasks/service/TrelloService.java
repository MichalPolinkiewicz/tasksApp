package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lenovo on 25.10.2017.
 */
@Service
public class TrelloService {

    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: new Trello Card";

    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto){

        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        if(newCard != null){
            simpleEmailService.send(
                    new Mail(
                            adminConfig.getAdminMail(),
                            null,
                            SUBJECT,
                            "new card " + trelloCardDto.getName() + " has been created on your account"
                    )
            );
        }

        return newCard;
    }
}
