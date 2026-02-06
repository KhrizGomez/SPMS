package com.app.backend.services.secondary.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.app.backend.dtos.secondary.SemestreDTO;
import com.app.backend.entities.secondary.Semestre;
import com.app.backend.repositories.secondary.ISemestreRepository;
import com.app.backend.services.secondary.ISemestreService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplSemestreService implements ISemestreService {
    private final ISemestreRepository semestreRepository;

    @Override
    public List<SemestreDTO> obtenerTodosLosSemestres() {
        List<Semestre> entidades = semestreRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public SemestreDTO guardarSemestre(@NonNull SemestreDTO semestreDTO) {
        Semestre entidad = new Semestre();
        entidad.setCodigoPeriodo(semestreDTO.getCodigoPeriodo());
        entidad.setNombrePeriodo(semestreDTO.getNombrePeriodo());
        entidad.setFechaInicio(semestreDTO.getFechaInicio());
        entidad.setFechaFin(semestreDTO.getFechaFin());
        entidad.setFechaInicioMatriculas(semestreDTO.getFechaInicioMatriculas());
        entidad.setFechaFinMatriculas(semestreDTO.getFechaFinMatriculas());
        entidad.setEstadoActivo(semestreDTO.getEstadoActivo());
        entidad.setEsPeriodoActual(semestreDTO.getEsPeriodoActual());

        Semestre entidadGuardada = semestreRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private SemestreDTO convertirADto(@NonNull Semestre entidad) {
        SemestreDTO dto = new SemestreDTO();
        dto.setIdSemestre(entidad.getIdSemestre());
        dto.setCodigoPeriodo(entidad.getCodigoPeriodo());
        dto.setNombrePeriodo(entidad.getNombrePeriodo());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setFechaInicioMatriculas(entidad.getFechaInicioMatriculas());
        dto.setFechaFinMatriculas(entidad.getFechaFinMatriculas());
        dto.setEstadoActivo(entidad.getEstadoActivo());
        dto.setEsPeriodoActual(entidad.getEsPeriodoActual());
        return dto;
    }
}
