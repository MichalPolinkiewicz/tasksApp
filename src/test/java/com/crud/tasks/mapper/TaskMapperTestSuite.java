package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
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
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //given
        TaskDto taskDto = new TaskDto(1, "testing", "testing");

        //when
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //then
        Assert.assertTrue(mappedTask.getClass().equals(Task.class));
        Assert.assertTrue(mappedTask.getId()==1);
        Assert.assertTrue(mappedTask.getTitle().equals("testing"));
        Assert.assertTrue(mappedTask.getContent().equals("testing"));
    }

    @Test
    public void mapToTaskDto(){
        //given
        Task task = new Task(1, "testing", "testing");

        //when
        TaskDto mappedTask = taskMapper.mapToTaskDto(task);

        //then
        Assert.assertTrue(mappedTask.getClass().equals(TaskDto.class));
        Assert.assertEquals(mappedTask.getTitle(), "testing");
        Assert.assertEquals(mappedTask.getContent(), "testing");
        Assert.assertTrue(mappedTask.getId()==1);
    }

    @Test
    public void mapToTaskDtoList(){
        //given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(0, "testing", "testing");
        taskList.add(task);

        //when
        List <TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);

        //then
        Assert.assertTrue(mappedList.size() == 1);
        Assert.assertTrue(mappedList.get(0).getTitle().equals("testing"));
        Assert.assertEquals(mappedList.get(0).getContent(), "testing");
        Assert.assertTrue(mappedList.get(0).getClass().equals(TaskDto.class));
    }

}
