package com.schedule_project_2.user.service;

import com.schedule_project_2.common.exception.BusinessException;
import com.schedule_project_2.common.exception.ErrorCode;
import com.schedule_project_2.schedule.entity.Schedule;
import com.schedule_project_2.schedule.repository.ScheduleRepository;
import com.schedule_project_2.user.dto.response.UserListResponse;
import com.schedule_project_2.user.dto.response.UserResponse;
import com.schedule_project_2.user.entity.User;
import com.schedule_project_2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public UserResponse createUser(String username, String email, String password){
        if(userRepository.existsByEmail(email))
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);

        User user = new User(username, email, password);

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

    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId){
        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));

        UserResponse userResponse = new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );

        return userResponse;
    }

    @Transactional(readOnly = true)
    public UserListResponse getAllUser(){
        List<User> userList = userRepository.findAllByDeletedAtIsNullOrderByCreatedAtDesc();

        List<UserResponse> userListResponse = new ArrayList<>();

        for( User user : userList ){
            userListResponse.add(new UserResponse(
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
                    )
            );
        }

        return new UserListResponse(userListResponse);
    }

    @Transactional
    public UserResponse updateUser(Long userId, Long loginUserId, String username){
        if(!userId.equals(loginUserId)){
            throw new BusinessException(ErrorCode.USER_FORBIDDEN);
        }

        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.update(username);

        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    @Transactional
    public void deleteUser(Long userId, Long loginUserId){
        if(!userId.equals(loginUserId)){
            throw new BusinessException(ErrorCode.USER_FORBIDDEN);
        }

        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<Schedule> scheduleList = scheduleRepository.findAllByUser_UserIdAndDeletedAtIsNullOrderByModifiedAtDesc(userId);
        for(Schedule schedule : scheduleList){
            schedule.delete();
        }

        user.delete();
    }
}
