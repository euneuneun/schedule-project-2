package com.schedule_project_2.user.controller;

import com.schedule_project_2.user.dto.request.UserCreateRequest;
import com.schedule_project_2.user.dto.response.UserResponse;
import com.schedule_project_2.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userservice;

    @PostMapping
    public ResponseEntity<UserResponse> createUsser(@Valid @RequestBody UserCreateRequest request){

        UserResponse response = userservice.createUser(request.getUsername(),request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
