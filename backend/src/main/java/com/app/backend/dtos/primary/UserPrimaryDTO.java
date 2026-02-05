package com.app.backend.dtos.primary;

import lombok.Data;

@Data
public class UserPrimaryDTO {
    private Integer idUser;
    private String names;
    private String surnames;
    private String cardId;
    private String institutionalEmail;
    private String personalMail;
    private Boolean statement;
    private String role;
    private Integer idConfiguration;
}
