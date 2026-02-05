package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.ConfigurationDTO;
import com.app.backend.entities.primary.Configuration;
import com.app.backend.repositories.primary.IConfigurationRepository;
import com.app.backend.services.primary.IConfigurationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplConfigurationService implements IConfigurationService {
    private final IConfigurationRepository configurationRepository;

    @Override
    public List<ConfigurationDTO> getAllConfigurations() {
        return configurationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConfigurationDTO getConfigurationById(Integer id) {
        return configurationRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Configuración no encontrada con ID: " + id));
    }

    @Override
    public ConfigurationDTO createConfiguration(@NonNull ConfigurationDTO configurationDTO) {
        Configuration entity = new Configuration();
        copyDtoToEntity(configurationDTO, entity);
        Configuration savedEntity = configurationRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public ConfigurationDTO updateConfiguration(Integer id, @NonNull ConfigurationDTO configurationDTO) {
        Configuration entity = configurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuración no encontrada con ID: " + id));
        copyDtoToEntity(configurationDTO, entity);
        Configuration savedEntity = configurationRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public void deleteConfiguration(Integer id) {
        if (!configurationRepository.existsById(id)) {
            throw new RuntimeException("Configuración no encontrada con ID: " + id);
        }
        configurationRepository.deleteById(id);
    }

    private ConfigurationDTO convertToDto(@NonNull Configuration entity) {
        ConfigurationDTO dto = new ConfigurationDTO();
        dto.setIdConfiguration(entity.getIdConfiguration());
        dto.setProfilePicture(entity.getProfilePicture());
        dto.setScannedSignature(entity.getScannedSignature());
        dto.setSmsChannel(entity.getSmsChannel());
        dto.setMailChannel(entity.getMailChannel());
        dto.setWhatsappChannel(entity.getWhatsappChannel());
        return dto;
    }

    private void copyDtoToEntity(@NonNull ConfigurationDTO dto, @NonNull Configuration entity) {
        entity.setProfilePicture(dto.getProfilePicture());
        entity.setScannedSignature(dto.getScannedSignature());
        entity.setSmsChannel(dto.getSmsChannel());
        entity.setMailChannel(dto.getMailChannel());
        entity.setWhatsappChannel(dto.getWhatsappChannel());
    }
}
