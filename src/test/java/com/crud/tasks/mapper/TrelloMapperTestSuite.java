package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Lenovo on 27.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardsFromBoardsDto(){
        //given
        List <TrelloBoardDto> testingListWithBoardDto = new ArrayList<>();
        List <TrelloListDto> list = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test",list );
        testingListWithBoardDto.add(0,trelloBoardDto);

        //when
        List <TrelloBoard> mappedList = trelloMapper.mapToBoards(testingListWithBoardDto);

        //then
        Assert.assertEquals(mappedList.get(0).getLists().size(), 0 );
        Assert.assertEquals(mappedList.get(0).getName(), "test");
        Assert.assertTrue(mappedList.get(0).getId().equals("1"));
        Assert.assertTrue(mappedList.get(0).getClass().equals(TrelloBoard.class));

        //clean up
        testingListWithBoardDto.remove(0);
    }

    @Test
    public void testMapToBoardsDto (){
        //given
        List <TrelloBoard> testingList = new ArrayList<>();
        List <TrelloList> emptyList = new ArrayList<>();
        TrelloBoard testingBoard = new TrelloBoard("0", "test", emptyList);
        testingList.add(0,testingBoard);

        //when
        List <TrelloBoardDto> mappedList = trelloMapper.mapToBoardsDto(testingList);

        //then
        Assert.assertTrue(mappedList.get(0).getClass().equals(TrelloBoardDto.class));
        Assert.assertEquals(mappedList.get(0).getName(), "test");
        Assert.assertEquals(mappedList.get(0).getId(), "0");

        //clean up
        testingList.remove(0);
    }

    @Test
    public void testMapToList (){
        //given
        List <TrelloListDto> testingList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("0", "test", false);
        testingList.add(trelloListDto);

        //when
        List <TrelloList> mappedList = trelloMapper.mapToList(testingList);

        //then
        Assert.assertEquals(mappedList.get(0).getId(), "0");
        Assert.assertEquals(mappedList.get(0).getName(), "test");
        Assert.assertTrue(mappedList.get(0).isClosed()==false);
        Assert.assertTrue(mappedList.get(0).getClass().equals(TrelloList.class));

        //cleanup
        testingList.remove(0);
    }
    @Test
    public void testMapToListDto (){
        //given
        List<TrelloList> testingList = new ArrayList<>();
        TrelloList trelloList = new TrelloList("0", "test", false);
        testingList.add(0,trelloList);

        //when
        List <TrelloListDto> mappedList = trelloMapper.mapToListDto(testingList);

        //then
        Assert.assertTrue(mappedList.get(0).getClass().equals(TrelloListDto.class));
        Assert.assertEquals(mappedList.get(0).getId(), "0");
        Assert.assertEquals(mappedList.get(0).getName(), "test");
        Assert.assertTrue(mappedList.get(0).isClosed()==false);

        //cleanup
        testingList.remove(0);
    }

    @Test
    public void testMapToCardDto(){
        //given
        TrelloCard testingCard = new TrelloCard("test", "test", "test", "test");
        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(testingCard);
        //then
        Assert.assertTrue(trelloCardDto.getClass().equals(TrelloCardDto.class));
        Assert.assertTrue(trelloCardDto.getDescription().equals("test"));
        Assert.assertTrue(trelloCardDto.getName().equals("test"));
        Assert.assertTrue(trelloCardDto.getPos().equals("test"));

        //cleanup
    }

    @Test
    public void testMapToCard(){
        //given
        TrelloCardDto testingCard = new TrelloCardDto("0", "test", "test", "test");
        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(testingCard);
        //then
        Assert.assertTrue(trelloCard.getClass().equals(TrelloCard.class));
        Assert.assertEquals(trelloCard.getDescription(), "test");
        Assert.assertEquals(trelloCard.getListId(), "test");
        Assert.assertEquals(testingCard.getPos(), "test");
        Assert.assertEquals(testingCard.getListId(), "test");
    }
}