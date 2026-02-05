package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer idUser;

    @Column(name = "names", length = 255)
    private String names;

    @Column(name = "surnames", length = 255)
    private String surnames;

    @Column(name = "cardid", length = 10, unique = true)
    private String cardId;

    @Column(name = "institutionalemail", length = 255, unique = true)
    private String institutionalEmail;

    @Column(name = "personalmail", length = 255)
    private String personalMail;

    @Column(name = "statement")
    private Boolean statement;

    @Column(name = "role", length = 255)
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Credentials credentials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configurationsidconfiguration")
    private Configuration configuration;
}
