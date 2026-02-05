package com.app.backend.dtos.primary;

import lombok.Data;

@Data
public class ConfigurationDTO {
    private Integer idConfiguration;
    private byte[] profilePicture;
    private byte[] scannedSignature;
    private Boolean smsChannel;
    private Boolean mailChannel;
    private Boolean whatsappChannel;
}
