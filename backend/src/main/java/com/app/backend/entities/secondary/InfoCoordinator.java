package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "info_coordinadores")
public class InfoCoordinator {
    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_usuario")
    private User CoordinatorUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Career FKInfoCarreraCoordinator;

    @Column(name = "oficina_atencion")
    private String oficinaAtencion;

    @Column(name = "estado")
    private Boolean estado;
}