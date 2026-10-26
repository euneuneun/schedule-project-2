package com.schedule_project_2.user.controller;

import com.schedule_project_2.common.SessionConst;
import com.schedule_project_2.user.dto.request.UserCreateRequest;
import com.schedule_project_2.user.dto.request.UserUpdateRequest;
import com.schedule_project_2.user.dto.response.UserListResponse;
import com.schedule_project_2.user.dto.response.UserResponse;
import com.schedule_project_2.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request){

        UserResponse response = userService.createUser(request.getUsername(), request.getEmail(), request.getPassword());

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

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @SessionAttribute(name = SessionConst.LOGIN_USER) Long loginUserId,
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request){

        UserResponse response = userService.updateUser(userId, loginUserId, request.getUsername());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @SessionAttribute(name = SessionConst.LOGIN_USER) Long loginUserId,
            @PathVariable Long userId,
            HttpServletRequest httpRequest){

        userService.deleteUser(userId, loginUserId);

        HttpSession session = httpRequest.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.noContent().build();
    }
}
