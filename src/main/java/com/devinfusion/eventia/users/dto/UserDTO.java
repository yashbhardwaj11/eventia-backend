package com.devinfusion.eventia.users.dto;

import com.devinfusion.eventia.users.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private String profilePictureUrl;
    private boolean active;
    private Role role;
}
