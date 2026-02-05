package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.CredentialsDTO;
import com.app.backend.services.primary.ICredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/primary/credentials")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CredentialsController {
    private final ICredentialsService credentialsService;

    @GetMapping
    public ResponseEntity<List<CredentialsDTO>> getAll() {
        return ResponseEntity.ok(credentialsService.getAllCredentials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialsDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(credentialsService.getCredentialsById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CredentialsDTO> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(credentialsService.getCredentialsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<CredentialsDTO> create(@RequestBody CredentialsDTO credentialsDTO) {
        CredentialsDTO savedCredentials = credentialsService.createCredentials(credentialsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCredentials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialsDTO> update(@PathVariable Integer id, @RequestBody CredentialsDTO credentialsDTO) {
        CredentialsDTO updatedCredentials = credentialsService.updateCredentials(id, credentialsDTO);
        return ResponseEntity.ok(updatedCredentials);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        credentialsService.deleteCredentials(id);
        return ResponseEntity.noContent().build();
    }
}
