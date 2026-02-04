package com.app.backend.services;

import com.app.backend.dtos.UserDTO;
import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO setUser(UserDTO userDTO);
}
