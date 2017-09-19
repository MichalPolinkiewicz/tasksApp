package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lenovo on 19.09.2017.
 */
public interface TaskRepository extends CrudRepository <Task, Long> {
    @Override
    List<Task> findAll();
}
