package com.example.DoodmonSarmayeProject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "response_responsible")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private User forUser;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User fromUser;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn
    private Request request;

    private String description;
}
