package com.example.backand.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private String typeOfWork;
    @Column(length = 2000)
    private String comment;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private LeadStatus status = LeadStatus.NEW;

    @Column(length = 2000)
    private String adminComment;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
