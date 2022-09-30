package com.brunopassos.desafioimpa.model.service;

import com.brunopassos.desafioimpa.model.domain.Task;
import com.brunopassos.desafioimpa.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Collection<Task> findAll() {
        return (Collection<Task>) taskRepository.findAll();
    }

    public List<Task> findAllByUser(Integer userId) {
        return taskRepository.findAllByUser(userId);
    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public Task done(Task task) {
        task.setDone(true);
        task.setDoneAt(LocalDateTime.now());
        return save(task);
    }
}
