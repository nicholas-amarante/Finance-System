package com.example.demo.models;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
}
