package com.schedule_project_2.auth.service;

import com.schedule_project_2.common.exception.BusinessException;
import com.schedule_project_2.common.exception.ErrorCode;
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
                .orElseThrow(()-> new BusinessException(ErrorCode.LOGIN_FAILED));

        if(!user.getPassword().equals(password)){
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }
        return user.getUserId();
    }
}
