package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lenovo on 27.10.2017.
 */
@Component
public class TrelloValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void validateCard (final TrelloCard trelloCard){

        if (trelloCard.getName().contains("test")){
            LOGGER.info("Someone is testing the app");
        } else {
            LOGGER.info("App is used in proper way");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards){
        LOGGER.info("Starting filtering boards...");
        List <TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards has been filtered. Current list size is: " + filteredBoards.size());

        return filteredBoards;
    }
}
