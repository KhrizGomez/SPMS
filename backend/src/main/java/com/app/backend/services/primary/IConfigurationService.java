package com.app.backend.services.primary;

import com.app.backend.dtos.primary.ConfigurationDTO;
import java.util.List;

public interface IConfigurationService {
    List<ConfigurationDTO> getAllConfigurations();
    ConfigurationDTO getConfigurationById(Integer id);
    ConfigurationDTO createConfiguration(ConfigurationDTO configurationDTO);
    ConfigurationDTO updateConfiguration(Integer id, ConfigurationDTO configurationDTO);
    void deleteConfiguration(Integer id);
}
