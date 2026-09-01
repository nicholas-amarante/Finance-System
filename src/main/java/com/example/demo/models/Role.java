package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="roles")
public class Role extends BaseModel {
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public RoleName getName() {return name;}
    public void setName(RoleName name) {this.name = name;}
}
