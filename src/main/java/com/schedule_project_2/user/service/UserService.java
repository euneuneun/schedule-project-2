package com.schedule_project_2.user.service;

import com.schedule_project_2.user.dto.response.UserResponse;
import com.schedule_project_2.user.entity.User;
import com.schedule_project_2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(String username, String email){

        User user = new User(username, email);

        User saveUser = userRepository.save(user);

        UserResponse response = new UserResponse(
                saveUser.getUserId(),
                saveUser.getUsername(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt()
        );

        return response;
    }

    public UserResponse getUser(Long userId){
        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));

        UserResponse userResponse = new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );

        return userResponse;
    }
}
