package com.schedule_project_2.auth.service;

import com.schedule_project_2.user.entity.User;
import com.schedule_project_2.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Long login(String email, String password){

        User user = userRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(()-> new IllegalStateException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if(!user.getPassword().equals(password)){
            throw new IllegalStateException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        return user.getUserId();
    }
}
