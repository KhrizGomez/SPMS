package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.ConfigurationDTO;
import com.app.backend.services.primary.IConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/primary/configurations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ConfigurationController {
    private final IConfigurationService configurationService;

    @GetMapping
    public ResponseEntity<List<ConfigurationDTO>> getAll() {
        return ResponseEntity.ok(configurationService.getAllConfigurations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(configurationService.getConfigurationById(id));
    }

    @PostMapping
    public ResponseEntity<ConfigurationDTO> create(@RequestBody ConfigurationDTO configurationDTO) {
        ConfigurationDTO savedConfiguration = configurationService.createConfiguration(configurationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConfiguration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigurationDTO> update(@PathVariable Integer id, @RequestBody ConfigurationDTO configurationDTO) {
        ConfigurationDTO updatedConfiguration = configurationService.updateConfiguration(id, configurationDTO);
        return ResponseEntity.ok(updatedConfiguration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        configurationService.deleteConfiguration(id);
        return ResponseEntity.noContent().build();
    }
}
