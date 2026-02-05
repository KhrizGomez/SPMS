package com.app.backend.services.primary;

import com.app.backend.dtos.primary.CredentialsDTO;
import java.util.List;

public interface ICredentialsService {
    List<CredentialsDTO> getAllCredentials();
    CredentialsDTO getCredentialsById(Integer id);
    CredentialsDTO getCredentialsByUserId(Integer userId);
    CredentialsDTO createCredentials(CredentialsDTO credentialsDTO);
    CredentialsDTO updateCredentials(Integer id, CredentialsDTO credentialsDTO);
    void deleteCredentials(Integer id);
}
