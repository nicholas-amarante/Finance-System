package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@Table(name="category")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel{
    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="user_category",  nullable = true)
    private User user;
}
