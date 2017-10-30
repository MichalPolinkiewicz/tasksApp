package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

/**
 * Created by Lenovo on 29.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void testShouldFetchEmptyList(){
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","test", false));

        List <TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        List <TrelloList> mappedTrelloList = new ArrayList<>();
        mappedTrelloList.add(new TrelloList("1", "test", false));

        List <TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());


        //when
        List <TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(0,trelloBoardDtos.size());

    }

    @Test
    public void shouldFetchTrelloBoards (){
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","list", false));

        List <TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "task", trelloLists));

        List <TrelloList> mappedTrelloList = new ArrayList<>();
        mappedTrelloList.add(new TrelloList("1", "list", false));

        List <TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "task", mappedTrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //when
        List <TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(1,trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            Assert.assertEquals(trelloBoardDto.getId(), "1");
            Assert.assertEquals(trelloBoardDto.getName(), "task");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assert.assertEquals("1", trelloListDto.getId());
                Assert.assertEquals("list", trelloListDto.getName());
                Assert.assertEquals(false, trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void testCreateCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card dto", "desc", "pos", "id");
        TrelloCard trelloCard = new TrelloCard("card", "desc", "pos", "id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "url");


        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        //when

        CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);

        //then
        Assert.assertTrue(createdCard.getName().equals("name"));
        Assert.assertTrue(createdCard.getShortUrl().equals("url"));
        Assert.assertTrue(createdCard.getId().equals("id"));

    }
}
