package com.astralxmedia.user;

import com.astralxmedia.user.dto.UserRegistrationRequest;

public interface UserService {
    User registerUser(UserRegistrationRequest registrationRequest);
}
