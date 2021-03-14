package com.example.DoodmonSarmayeProject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "request_investor")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn
    private User user;

    @NotBlank(message = "شرح درخواست نمی تواند خالی باشد")
    @Size(max = 4000, message = "طول پیام بیشتر از حد تعیین شده است")
    private String description;
}
