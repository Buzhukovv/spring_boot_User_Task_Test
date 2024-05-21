package com.example.demo.controller;
import com.example.demo.model.Task;
import com.example.demo.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {//создать запись
        return taskService.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable long id, @RequestBody Task task) {//редактирование записи по id
        task.setId(id);
        return taskService.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable long id) {//удалить запись по id
        taskService.deleteById(id);
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable long id) {//получить запись по id
        return  taskService.findById(id);
    }

    @GetMapping("/tasks")
    public Iterable<Task> get() {//получить все записи
        return taskService.findAll();
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable Long id) {
        taskService.markAsDone(id);
    }
}