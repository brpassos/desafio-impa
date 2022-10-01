package com.brunopassos.desafioimpa.controller;

import com.brunopassos.desafioimpa.model.domain.Task;
import com.brunopassos.desafioimpa.model.domain.User;
import com.brunopassos.desafioimpa.model.service.TaskService;
import com.brunopassos.desafioimpa.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Operation(summary = "Cadastrar uma tarefa para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa cadastrada",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                }
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                    content = @Content
            )
    })
    @PostMapping("/user/{user_id}")
    public ResponseEntity<Object> insert(@PathVariable(value = "user_id") Integer userId, @RequestBody Task task) {
        try {

            Optional<User> userOptional = userService.findById(userId);

            if(userOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }

            task.setUser(userOptional.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir a tarefa!");
        }
    }

    @Operation(summary = "Listar todas as tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas",
                    content = {
                            @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Task.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<Object> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as tarefas!");
        }
    }

    @Operation(summary = "Listar as tarefas de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class)))
                    }
            )
    })
    @GetMapping("/user/{user_id}")
    public ResponseEntity<Object> getAllByUser(@PathVariable(value = "user_id") Integer userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllByUser(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as tarefas!");
        }
    }

    @Operation(summary = "Detalhar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(taskOptional.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar buscar a tarefa!");
        }
    }

    @Operation(summary = "Alterar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alterada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada!",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Task task) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
            }

            Task currentTask = taskOptional.get();
            if (task.getTitle() != null) currentTask.setTitle(task.getTitle());
            if (task.getDescription() != null) currentTask.setDescription(task.getDescription());

            return ResponseEntity.status(HttpStatus.OK).body(taskService.save(currentTask));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar atualizar a tarefa!");
        }
    }

    @Operation(summary = "Deletar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa deletada!",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada!",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
            }

            taskService.delete(taskOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar deletar a tarefa!");
        }
    }

    @Operation(summary = "Concluir uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa concluída!",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada!",
                    content = @Content
            )
    })
    @GetMapping("/done/{id}")
    public ResponseEntity<Object> done(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Task> taskOptional = taskService.findById(id);

            if(taskOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(taskService.done(taskOptional.get()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar concluir a tarefa!");
        }
    }
}
