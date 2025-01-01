package com.devinfusion.eventia.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devinfusion.eventia.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    List<User> findByEmail(String email);
}
