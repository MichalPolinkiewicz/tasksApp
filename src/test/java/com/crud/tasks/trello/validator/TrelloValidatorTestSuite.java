package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 30.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateBoards (){
        //given
        List<TrelloBoard> boardList = new ArrayList<>();
        List <TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard testBoard = new TrelloBoard("0", "test", trelloLists);
        TrelloBoard secondTestBoard = new TrelloBoard("1", "test board", trelloLists);
        TrelloBoard okBoard = new TrelloBoard("2", "this is board",trelloLists);

        boardList.add(testBoard);
        boardList.add(secondTestBoard);
        boardList.add(okBoard);

        //when
        List <TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(boardList);

        //then
        Assert.assertEquals(validatedList.size(), 1);

    }
}
