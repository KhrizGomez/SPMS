package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcredentials")
    private Integer idCredentials;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "datemodification")
    @Temporal(TemporalType.DATE)
    private Date dateModification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credentialsidcredentials", referencedColumnName = "iduser")
    private User user;
}
