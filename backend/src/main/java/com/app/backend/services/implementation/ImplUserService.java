package com.app.backend.services.implementation;

import com.app.backend.dtos.UserDTO;
import com.app.backend.entities.secondary.User;
import com.app.backend.repositories.secondary.IUserRepository;
import com.app.backend.services.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplUserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> entities = userRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO setUser(@NonNull UserDTO userDTO) {
        User entity = new User();
        BeanUtils.copyProperties(userDTO, entity);
        User savedEntity = userRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private UserDTO convertToDto(@NonNull User entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
