package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.UserDTO;
import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO setUser(UserDTO userDTO);
}
