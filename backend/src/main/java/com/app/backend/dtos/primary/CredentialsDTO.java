package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

@Data
public class CredentialsDTO {
    private Integer idCredentials;
    private String password;
    private Date dateModification;
    private Integer idUser;
}
