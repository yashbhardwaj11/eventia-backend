package com.devinfusion.eventia.users.service;

import com.devinfusion.eventia.users.dto.UserDTO;
import com.devinfusion.eventia.users.entity.User;

public interface UserService {
    UserDTO create(User user);
    UserDTO update(String uid, User user);
    void delete(String uid);
    UserDTO get(String uid);
}
