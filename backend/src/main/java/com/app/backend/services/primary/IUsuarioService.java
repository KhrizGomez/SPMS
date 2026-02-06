package com.app.backend.services.primary;

import com.app.backend.dtos.primary.UsuarioDTO;
import java.util.List;

public interface IUsuarioService {
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    UsuarioDTO obtenerUsuarioPorId(Integer id);
    UsuarioDTO obtenerUsuarioPorCedula(String cedula);
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(Integer id);
}
