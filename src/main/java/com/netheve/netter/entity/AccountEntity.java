package com.netheve.netter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netheve.netter.entity.enums.AccountRank;
import com.netheve.netter.entity.enums.AccountRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 16)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRank rank;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @Column
    @JsonIgnore
    private String authToken;

    @Column
    @JsonIgnore
    private Long tokenExpiredAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
