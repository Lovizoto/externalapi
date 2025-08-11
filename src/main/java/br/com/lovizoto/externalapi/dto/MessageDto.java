package br.com.lovizoto.externalapi.dto;

import jakarta.validation.constraints.NotBlank;

public class MessageDto {

    @NotBlank
    private String externalId;
    @NotBlank
    private String source;
    @NotBlank
    private String message;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
