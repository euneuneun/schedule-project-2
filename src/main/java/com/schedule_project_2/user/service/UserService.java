package com.schedule_project_2.user.service;

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
            throw new IllegalStateException("존재하는 이메일 입니다.");

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
            throw new IllegalStateException("본인의 정보만 수정할 수 있습니다.");
        }

        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));

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
            throw new IllegalStateException("본인만 탈퇴할 수 있습니다.");
        }

        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));

        List<Schedule> scheduleList = scheduleRepository.findAllByUser_UserIdAndDeletedAtIsNullOrderByModifiedAtDesc(userId);
        for(Schedule schedule : scheduleList){
            schedule.delete();
        }

        user.delete();
    }
}
