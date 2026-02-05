package com.app.backend.services.primary;

import com.app.backend.dtos.primary.UserPrimaryDTO;
import java.util.List;

public interface IUserPrimaryService {
    List<UserPrimaryDTO> getAllUsers();
    UserPrimaryDTO getUserById(Integer id);
    UserPrimaryDTO getUserByCardId(String cardId);
    UserPrimaryDTO createUser(UserPrimaryDTO userDTO);
    UserPrimaryDTO updateUser(Integer id, UserPrimaryDTO userDTO);
    void deleteUser(Integer id);
}
