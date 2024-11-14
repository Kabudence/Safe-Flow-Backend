package com.safeflow.IAM.service.impl;

import com.safeflow.IAM.dto.SigninRequest;
import com.safeflow.IAM.dto.SignupRequest;
import com.safeflow.IAM.dto.UserDTO;
import com.safeflow.IAM.model.User;
import com.safeflow.IAM.repository.UserRepository;
import com.safeflow.IAM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String signup(SignupRequest request) {
        // Check if username or email already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent() ||
                userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }

        // Create new user without encoding the password
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())  // Directly save plain text password (not recommended for production)
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public boolean signin(SigninRequest request) {
        // Find user by username
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        // Check if user exists and if passwords match (plain text comparison)
        if (userOpt.isPresent() && request.getPassword().equals(userOpt.get().getPassword())) {
            return true;  // User authenticated successfully
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    // Convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
