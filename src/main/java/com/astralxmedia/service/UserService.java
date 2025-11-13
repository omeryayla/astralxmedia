package com.astralxmedia.service;

import com.astralxmedia.dto.UserRegistrationRequest;
import com.astralxmedia.dto.UserResponse;
import com.astralxmedia.entity.User;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest registrationRequest);
}
