package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.UsuarioSecundarioDTO;
import java.util.List;

public interface IUsuarioSecundarioService {
    List<UsuarioSecundarioDTO> obtenerTodosLosUsuarios();
    UsuarioSecundarioDTO guardarUsuario(UsuarioSecundarioDTO usuarioDTO);
}
