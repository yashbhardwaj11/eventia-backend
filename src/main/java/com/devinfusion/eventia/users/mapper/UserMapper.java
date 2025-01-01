package com.devinfusion.eventia.users.mapper;

import com.devinfusion.eventia.users.dto.UserDTO;
import com.devinfusion.eventia.users.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
            user.getUid(),
            user.getName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getProfilePictureUrl(),
            user.isActive(),
            user.getRole()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUid(userDTO.getUid());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());
        user.setActive(userDTO.isActive());
        user.setRole(userDTO.getRole());
        return user;
    }
}
