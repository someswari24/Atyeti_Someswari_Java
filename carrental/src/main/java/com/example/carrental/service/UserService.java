package com.example.carrental.service;

import com.example.carrental.model.User;
import com.example.carrental.model.enums.Role;
import com.example.carrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(User user){
       log.info("Creating User :"+user.getUsername());
        return userRepository.save(user);
    }

    public Optional<User>findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User>findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findUsersByRole(Role role){
        return userRepository.findUsersByRole(role);
    }

    public List<User>findAllUsers(){
        return userRepository.findAll();
    }
}
