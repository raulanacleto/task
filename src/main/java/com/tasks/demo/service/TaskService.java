package com.tasks.demo.service;

import com.tasks.demo.domain.Task;
import com.tasks.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> findByDescription(Pageable pageable, String description) {
        return taskRepository.findByDescription(pageable, description);
    }

    public Task create(Task task) {
        task.setCreatedAt(new Date());
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        if (Objects.nonNull(taskRepository.findById(id))) {
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public Task alterarStatus(Long id, Task task) {
        Task tarefa = taskRepository.findById(id).orElse(null);
        if (Objects.nonNull(tarefa)) {
            tarefa.setFinished(task.getFinished());
            return taskRepository.save(tarefa);
        } else {
            return null;
        }
    }

    public Boolean deletar(Long id) {
        Task tarefa = taskRepository.findById(id).orElse(null);
        if (Objects.nonNull(tarefa)) {
            taskRepository.delete(tarefa);
            return true;
        } else {
            return false;
        }
    }

}
