package com.netheve.netter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "letter_in")
public class LetterInEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String agenda;

    @Column
    private String letterNumber;

    @Column
    private LocalDateTime forDate;

    @Column
    private String letterType;

    @Column
    private String sender;

    @Column
    private String issuer;

    @Column
    private String description;

    @Column
    private String fileUrl;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
