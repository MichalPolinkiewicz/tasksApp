package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Created by Lenovo on 29.10.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    DbService dbService;
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testSaveTask(){

        //given
        int size = dbService.getAllTasks().size();
        //when
        Task testingTask = new Task(0, "task", "content");
        dbService.saveTask(testingTask);
        int newSize = dbService.getAllTasks().size();
        //then
        Assert.assertTrue(newSize == size+1);
        //clean up
        taskRepository.deleteAll();
    }

    @Test
    public void testDeleteTask(){
        //given
        Task task = new Task(0, "test", "test");
        dbService.saveTask(task);
        long id = dbService.getAllTasks().listIterator().next().getId();
        int size = dbService.getAllTasks().size();

        //when
        dbService.deleteTask(id);
        int newSize = dbService.getAllTasks().size();

        //then
        Assert.assertTrue(newSize==size-1);
        //clean up
        taskRepository.deleteAll();
    }

    @Test
    public void testGetTask(){
        //given
        Task task = new Task(0,"testing task - title", "testing task - content");
        dbService.saveTask(task);
        long id = task.getId();

        //when
        Optional <Task> taskFromDb = dbService.getTask(id);

        //then
        Assert.assertTrue(taskFromDb.isPresent());
        Assert.assertTrue(taskFromDb.get().getTitle().equals("testing task - title"));
        Assert.assertTrue(taskFromDb.get().getContent().equals("testing task - content"));

        //clean up
        taskRepository.deleteAll();

    }


}
