package com.brunopassos.desafioimpa.controller;

import com.brunopassos.desafioimpa.model.domain.Task;
import com.brunopassos.desafioimpa.model.domain.User;
import com.brunopassos.desafioimpa.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Cadastre um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir o usuário!");
        }
    }
}
