package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.UserPrimaryDTO;
import com.app.backend.entities.primary.User;
import com.app.backend.repositories.primary.IConfigurationRepository;
import com.app.backend.repositories.primary.IUserPrimaryRepository;
import com.app.backend.services.primary.IUserPrimaryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplUserPrimaryService implements IUserPrimaryService {
    private final IUserPrimaryRepository userRepository;
    private final IConfigurationRepository configurationRepository;

    @Override
    public List<UserPrimaryDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserPrimaryDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public UserPrimaryDTO getUserByCardId(String cardId) {
        return userRepository.findByCardId(cardId)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con cédula: " + cardId));
    }

    @Override
    public UserPrimaryDTO createUser(@NonNull UserPrimaryDTO userDTO) {
        User entity = new User();
        copyDtoToEntity(userDTO, entity);
        User savedEntity = userRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public UserPrimaryDTO updateUser(Integer id, @NonNull UserPrimaryDTO userDTO) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        copyDtoToEntity(userDTO, entity);
        User savedEntity = userRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserPrimaryDTO convertToDto(@NonNull User entity) {
        UserPrimaryDTO dto = new UserPrimaryDTO();
        dto.setIdUser(entity.getIdUser());
        dto.setNames(entity.getNames());
        dto.setSurnames(entity.getSurnames());
        dto.setCardId(entity.getCardId());
        dto.setInstitutionalEmail(entity.getInstitutionalEmail());
        dto.setPersonalMail(entity.getPersonalMail());
        dto.setStatement(entity.getStatement());
        dto.setRole(entity.getRole());
        if (entity.getConfiguration() != null) {
            dto.setIdConfiguration(entity.getConfiguration().getIdConfiguration());
        }
        return dto;
    }

    private void copyDtoToEntity(@NonNull UserPrimaryDTO dto, @NonNull User entity) {
        entity.setNames(dto.getNames());
        entity.setSurnames(dto.getSurnames());
        entity.setCardId(dto.getCardId());
        entity.setInstitutionalEmail(dto.getInstitutionalEmail());
        entity.setPersonalMail(dto.getPersonalMail());
        entity.setStatement(dto.getStatement());
        entity.setRole(dto.getRole());
        if (dto.getIdConfiguration() != null) {
            configurationRepository.findById(dto.getIdConfiguration())
                    .ifPresent(entity::setConfiguration);
        }
    }
}
