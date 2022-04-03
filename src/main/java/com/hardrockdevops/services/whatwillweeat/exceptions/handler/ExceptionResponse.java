package com.hardrockdevops.services.whatwillweeat.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
