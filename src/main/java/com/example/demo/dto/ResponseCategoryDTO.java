package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public record ResponseCategoryDTO(
        @NotNull(message = "O nome não pode ser nulo para categoria!")
        String name
) {
}
