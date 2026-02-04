package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "info_decanos")
public class InfoDean {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User FKDeanUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Faculty FKInfoFacultadDean;

    @Column(name = "fecha_nombramiento")
    private Date FechaNombramiento;

    @Column(name = "estado")
    private Boolean Estado;
}
