package com.astralxmedia.service;

import com.astralxmedia.entity.User;
import com.astralxmedia.repository.UserRepository;
import com.astralxmedia.dto.UserRegistrationRequest;
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
