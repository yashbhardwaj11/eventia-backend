package com.devinfusion.eventia.users.controller;

import com.devinfusion.eventia.dto.SuccessDTO;
import com.devinfusion.eventia.users.dto.UserDTO;
import com.devinfusion.eventia.users.entity.User;
import com.devinfusion.eventia.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<SuccessDTO<UserDTO>> createUser(@RequestBody User user) {
        UserDTO savedUser = userService.create(user);
        SuccessDTO<UserDTO> response = new SuccessDTO<>(201, "User created successfully", savedUser);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<SuccessDTO<UserDTO>> updateUser(@PathVariable String uid,@RequestBody User user) {
        UserDTO updatedUser = userService.update(uid, user);
        SuccessDTO<UserDTO> response = new SuccessDTO<>(200, "User updated successfully", updatedUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<SuccessDTO<UserDTO>> getUser(@PathVariable String uid) {
        UserDTO user = userService.get(uid);
        SuccessDTO<UserDTO> response = new SuccessDTO<>(200, "User fetched successfully", user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<SuccessDTO<String>> deleteUser(@PathVariable String uid) {
        userService.delete(uid);
        SuccessDTO<String> response = new SuccessDTO<>(200, "User deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
