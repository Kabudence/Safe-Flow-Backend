package com.safeflow.IAM.service;

import com.safeflow.IAM.dto.SigninRequest;
import com.safeflow.IAM.dto.SignupRequest;
import com.safeflow.IAM.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String signup(SignupRequest request);
    boolean signin(SigninRequest request);
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
}