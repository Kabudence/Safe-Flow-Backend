package com.safeflow.IAM.dto;

import com.safeflow.IAM.model.valueobjects.UserRole;
import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String fullName;
    private String email;
    private String password;
    private UserRole role;
}
