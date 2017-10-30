package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Lenovo on 30.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testCreateTrelloCard (){
        //gievn
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "0", "0");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("0", "name", "url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test");

        //when
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);

        //then
        Assert.assertTrue(createdCard.getName().equals("name"));
        Assert.assertTrue(createdCard.getShortUrl().equals("url"));
        Assert.assertTrue(createdCard.getId().equals("0"));
    }

    @Test
    public void testFetchTrelloBoards(){
        //given
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);

        //when
        List <TrelloBoardDto> list = trelloService.fetchTrelloBoards();

        //then
        Assert.assertTrue(list.size()==0);

    }
}
