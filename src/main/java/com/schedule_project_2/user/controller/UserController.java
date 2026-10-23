package com.schedule_project_2.user.controller;

import com.schedule_project_2.user.dto.request.UserCreateRequest;
import com.schedule_project_2.user.dto.response.UserListResponse;
import com.schedule_project_2.user.dto.response.UserResponse;
import com.schedule_project_2.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUsser(@Valid @RequestBody UserCreateRequest request){

        UserResponse response = userService.createUser(request.getUsername(),request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId){

        UserResponse response = userService.getUser(userId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserListResponse> getAllUser(){

        UserListResponse response = userService.getAllUser();

        return ResponseEntity.ok(response);
    }
}
