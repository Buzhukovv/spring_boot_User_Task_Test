package com.example.demo.services.impl;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
@Builder
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findById(long id) {
        return taskRepository.findAllById(id);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void markAsDone(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDone(true);
            taskRepository.save(task);
        }
    }
}
