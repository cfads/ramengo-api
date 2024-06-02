package com.example.ramengo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Broth {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "image_inactive")
    private String imageInactive;

    @Column(name = "image_active")
    private String imageActive;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;
}
