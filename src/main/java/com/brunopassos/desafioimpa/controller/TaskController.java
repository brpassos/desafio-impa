package com.brunopassos.desafioimpa.controller;

import com.brunopassos.desafioimpa.model.domain.Task;
import com.brunopassos.desafioimpa.model.domain.User;
import com.brunopassos.desafioimpa.model.service.TaskService;
import com.brunopassos.desafioimpa.model.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{user_id}")
    public ResponseEntity<Object> insert(@PathVariable(value = "user_id") Integer userId, @RequestBody Task task) {
        try {

            Optional<User> userOptional = userService.findById(userId);

            if(userOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não existe!");
            }

            task.setUser(userOptional.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir a tarefa!");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as tarefas!");
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Object> getAllByUser(@PathVariable(value = "user_id") Integer userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllByUser(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as tarefas!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(taskOptional.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar buscar a tarefa!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Task task) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe!");
            }

            Task currentTask = taskOptional.get();
            BeanUtils.copyProperties(task, currentTask, "id");

            return ResponseEntity.status(HttpStatus.OK).body(taskService.save(currentTask));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar atualizar a tarefa!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe!");
            }

            taskService.delete(taskOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar deletar a tarefa!");
        }
    }

    @GetMapping("/done/{id}")
    public ResponseEntity<Object> done(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(taskService.done(taskOptional.get()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar concluir a tarefa!");
        }
    }
}
