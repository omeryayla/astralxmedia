package com.astralxmedia.service;

import com.astralxmedia.dto.UserRegistrationRequest;
import com.astralxmedia.entity.User;

public interface UserService {
    User registerUser(UserRegistrationRequest registrationRequest);
}
