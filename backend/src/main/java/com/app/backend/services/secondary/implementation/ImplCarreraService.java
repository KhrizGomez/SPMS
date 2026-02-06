package com.app.backend.services.secondary.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.backend.dtos.secondary.CarreraDTO;
import com.app.backend.entities.secondary.Carrera;
import com.app.backend.repositories.secondary.ICarreraRepository;
import com.app.backend.repositories.secondary.IFacultadRepository;
import com.app.backend.services.secondary.ICarreraService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio para Carrera.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplCarreraService implements ICarreraService {
    private final ICarreraRepository carreraRepository;
    private final IFacultadRepository facultadRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<CarreraDTO> obtenerTodasLasCarreras() {
        List<Carrera> entidades = carreraRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public CarreraDTO guardarCarrera(@NonNull CarreraDTO carreraDTO) {
        Carrera entidad = new Carrera();

        entidad.setNombreCarrera(carreraDTO.getNombreCarrera());
        entidad.setCodigoCarrera(carreraDTO.getCodigoCarrera());
        entidad.setDuracionSemestres(carreraDTO.getDuracionSemestres());
        entidad.setModalidad(carreraDTO.getModalidad());
        entidad.setTituloOtorga(carreraDTO.getTituloOtorga());
        entidad.setEstado(carreraDTO.getEstado());

        if (carreraDTO.getIdFacultad() != null) {
            facultadRepository.findById(carreraDTO.getIdFacultad()).ifPresent(entidad::setFacultad);
        }

        Carrera entidadGuardada = carreraRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private CarreraDTO convertirADto(@NonNull Carrera entidad) {
        CarreraDTO dto = new CarreraDTO();
        dto.setIdCarrera(entidad.getIdCarrera());
        dto.setNombreCarrera(entidad.getNombreCarrera());
        dto.setCodigoCarrera(entidad.getCodigoCarrera());
        dto.setDuracionSemestres(entidad.getDuracionSemestres());
        dto.setModalidad(entidad.getModalidad());
        dto.setTituloOtorga(entidad.getTituloOtorga());
        dto.setEstado(entidad.getEstado());

        if (entidad.getFacultad() != null) {
            dto.setIdFacultad(entidad.getFacultad().getIdFacultad());
            dto.setNombreFacultad(entidad.getFacultad().getNombreFacultad());
        }
        return dto;
    }
}
