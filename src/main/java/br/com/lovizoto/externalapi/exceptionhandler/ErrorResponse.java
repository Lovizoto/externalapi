package br.com.lovizoto.externalapi.exceptionhandler;

import java.time.LocalDateTime;


public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message) {
        this.message = message;
    }


}
