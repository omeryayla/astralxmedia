package com.astralxmedia.user.service;

import com.astralxmedia.user.User;
import com.astralxmedia.user.UserRepository;
import com.astralxmedia.user.UserService;
import com.astralxmedia.user.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(UserRegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .build();
        return userRepository.save(user);
    }
}
