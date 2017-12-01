package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Lenovo on 31.10.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService dbService;

    @Test
    public void testGetTasks() throws Exception {
        //given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1, "title", "content"));

        List <Task> tasksList = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(tasksList);
        when(taskMapper.mapToTaskDtoList(tasksList)).thenReturn(taskDtoList);

        //when & then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }


    public void testGetTask ()throws Exception{
        //given
        long id = 0;
        TaskDto taskDto = new TaskDto(id, "title", "content");
        Task task = new Task(id, "", "");

       when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
       when(dbService.getTask(id)).thenReturn(Optional.of(task));


        //when & then
        mockMvc.perform(get("/v1/tasks/{0}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")))
                .andExpect(jsonPath("$.taskId", is(0)));
    }


    @Test
    public void testCreateTask () throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1, "aa", "aa");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteTask () throws Exception{
        // given

        // when & then
        mockMvc.perform(delete("/v1/task{0}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateTask()throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1, "title", "content");
        TaskDto updatedTask = new TaskDto(1, "update title", "updated content");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(updatedTask);

        Gson gson = new Gson();
        String cont = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(cont))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("update title")))
                .andExpect(jsonPath("$.content", is("updated content")));
    }

}