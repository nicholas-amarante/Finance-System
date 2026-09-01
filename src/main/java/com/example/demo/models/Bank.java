package com.example.demo.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="banks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank extends BaseModel {
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
