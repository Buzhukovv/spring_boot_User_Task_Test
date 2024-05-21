package com.example.demo.services;

import com.example.demo.model.Task;

import java.util.Optional;

public interface TaskService {

    Task save(Task task);

    void deleteById(long id);

    Task findById(long id);

    Iterable<Task> findAll();

    void markAsDone(Long id);
}
