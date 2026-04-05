package com.example.demo.dto;

import com.example.demo.models.Role;

import java.util.List;

public record RecoveryUserDTO(
        Long id,
        String email,
        List<Role> roles
    )
{}
