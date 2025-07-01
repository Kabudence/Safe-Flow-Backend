package com.safeflow.IAM.service.impl;

import com.safeflow.IAM.dto.SigninRequest;
import com.safeflow.IAM.dto.SignupRequest;
import com.safeflow.IAM.dto.UserDTO;
import com.safeflow.IAM.model.User;
import com.safeflow.IAM.model.valueobjects.UserRole;
import com.safeflow.IAM.repository.UserRepository;
import com.safeflow.IAM.service.UserService;
import com.safeflow.domain.model.Employee;
import com.safeflow.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent() ||
                userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())  // No codifiques el password en producción
                .build();

        user = userRepository.save(user);

        if (request.getRole() == UserRole.EMPLOYEE) {
            Employee employee = Employee.builder()
                    .userId(user.getId())
                    .fullName(request.getFullName())
                    .build();
            employeeRepository.save(employee);
        }

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
