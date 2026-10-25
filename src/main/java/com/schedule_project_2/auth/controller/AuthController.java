package com.schedule_project_2.auth.controller;

import com.schedule_project_2.auth.dto.LoginRequest;
import com.schedule_project_2.auth.service.AuthService;
import com.schedule_project_2.common.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest ){
        // 1. 이메일, 비밀번호 받아오기
        // 2. 데이터베이스에 해당 유저가 존재하는지 확인
        // 3. 비밀번호 검증
        // 4. 세션 키 자동 생성(자동)
        Long userId = authservice.login(request.getEmail(), request.getPassword());

        // 유저 섹션 데이터 생성(반 자동)
        HttpSession session = httpRequest.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, userId);

        return ResponseEntity.ok().build();
    }

}
