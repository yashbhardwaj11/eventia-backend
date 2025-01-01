package com.devinfusion.eventia.users.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.devinfusion.eventia.users.dto.UserDTO;
import com.devinfusion.eventia.users.entity.User;
import com.devinfusion.eventia.users.exceptions.UserAlreadyExists;
import com.devinfusion.eventia.users.exceptions.UserNotFound;
import com.devinfusion.eventia.users.mapper.UserMapper;
import com.devinfusion.eventia.users.repository.UserRepository;
import com.devinfusion.eventia.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(User user) {
        checkDuplicateEmail(user.getEmail(), null);
        user.setUid(UUID.randomUUID().toString());
        User savedUser = userRepository.save(user);
        System.out.println("SAVED USER : " + savedUser);
        System.out.println("REQUESTED USER : " + user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO update(String uid, User user) {
        User existingUser = userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFound("User with id " + uid + " not found"));

        checkDuplicateEmail(user.getEmail(), uid);

        if (user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public void delete(String uid) {
        userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFound("User with id " + uid + " not found"));
        userRepository.deleteById(uid);
    }

    @Override
    public UserDTO get(String uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFound("User with id " + uid + " not found"));

        return UserMapper.toDTO(user);
    }

    private void checkDuplicateEmail(String email, String uid) {
        List<User> users = userRepository.findByEmail(email);

        if (!users.isEmpty()) {
            if (uid == null || !users.get(0).getUid().equals(uid)) {
                throw new UserAlreadyExists("User with email: " + email + " already exists.");
            }
        }
    }
}
