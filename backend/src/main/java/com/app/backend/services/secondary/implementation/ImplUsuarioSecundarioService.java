package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.UsuarioSecundarioDTO;
import com.app.backend.entities.secondary.Usuario;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.IUsuarioSecundarioService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplUsuarioSecundarioService implements IUsuarioSecundarioService {
    private final IUsuarioSecundarioRepository usuarioRepository;

    @Override
    public List<UsuarioSecundarioDTO> obtenerTodosLosUsuarios() {
        List<Usuario> entidades = usuarioRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioSecundarioDTO guardarUsuario(@NonNull UsuarioSecundarioDTO usuarioDTO) {
        Usuario entidad = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, entidad);
        Usuario entidadGuardada = usuarioRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private UsuarioSecundarioDTO convertirADto(@NonNull Usuario entidad) {
        UsuarioSecundarioDTO dto = new UsuarioSecundarioDTO();
        BeanUtils.copyProperties(entidad, dto);
        return dto;
    }
}
