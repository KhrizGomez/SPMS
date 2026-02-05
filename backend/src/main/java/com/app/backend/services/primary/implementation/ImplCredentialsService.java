package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.CredentialsDTO;
import com.app.backend.entities.primary.Credentials;
import com.app.backend.repositories.primary.ICredentialsRepository;
import com.app.backend.repositories.primary.IUserPrimaryRepository;
import com.app.backend.services.primary.ICredentialsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplCredentialsService implements ICredentialsService {
    private final ICredentialsRepository credentialsRepository;
    private final IUserPrimaryRepository userRepository;

    @Override
    public List<CredentialsDTO> getAllCredentials() {
        return credentialsRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CredentialsDTO getCredentialsById(Integer id) {
        return credentialsRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas con ID: " + id));
    }

    @Override
    public CredentialsDTO getCredentialsByUserId(Integer userId) {
        return credentialsRepository.findByUser_IdUser(userId)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas para el usuario: " + userId));
    }

    @Override
    public CredentialsDTO createCredentials(@NonNull CredentialsDTO credentialsDTO) {
        Credentials entity = new Credentials();
        copyDtoToEntity(credentialsDTO, entity);
        entity.setDateModification(new Date());
        Credentials savedEntity = credentialsRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public CredentialsDTO updateCredentials(Integer id, @NonNull CredentialsDTO credentialsDTO) {
        Credentials entity = credentialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas con ID: " + id));
        copyDtoToEntity(credentialsDTO, entity);
        entity.setDateModification(new Date());
        Credentials savedEntity = credentialsRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public void deleteCredentials(Integer id) {
        if (!credentialsRepository.existsById(id)) {
            throw new RuntimeException("Credenciales no encontradas con ID: " + id);
        }
        credentialsRepository.deleteById(id);
    }

    private CredentialsDTO convertToDto(@NonNull Credentials entity) {
        CredentialsDTO dto = new CredentialsDTO();
        dto.setIdCredentials(entity.getIdCredentials());
        dto.setPassword(entity.getPassword());
        dto.setDateModification(entity.getDateModification());
        if (entity.getUser() != null) {
            dto.setIdUser(entity.getUser().getIdUser());
        }
        return dto;
    }

    private void copyDtoToEntity(@NonNull CredentialsDTO dto, @NonNull Credentials entity) {
        entity.setPassword(dto.getPassword());
        if (dto.getIdUser() != null) {
            userRepository.findById(dto.getIdUser())
                    .ifPresent(entity::setUser);
        }
    }
}
