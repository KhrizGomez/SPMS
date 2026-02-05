package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "configurations")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconfiguration")
    private Integer idConfiguration;

    @Lob
    @Column(name = "profilepicture", columnDefinition = "bytea")
    private byte[] profilePicture;

    @Lob
    @Column(name = "scannedsignature", columnDefinition = "bytea")
    private byte[] scannedSignature;

    @Column(name = "smschannel")
    private Boolean smsChannel;

    @Column(name = "mailchannel")
    private Boolean mailChannel;

    @Column(name = "whatsappchannel")
    private Boolean whatsappChannel;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
