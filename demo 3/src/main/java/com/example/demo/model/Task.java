package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDate date;

    private String description;

    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
