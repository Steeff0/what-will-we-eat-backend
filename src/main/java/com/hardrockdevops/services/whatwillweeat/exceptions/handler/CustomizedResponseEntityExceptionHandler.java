package com.hardrockdevops.services.whatwillweeat.exceptions.handler;

import com.hardrockdevops.services.whatwillweeat.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ServiceException.class
    })
    public final ResponseEntity<ExceptionResponse> handleCustomExceptions(Exception e, WebRequest request) {
        if (e instanceof ServiceException) {
            return handleCustomException(e, HttpStatus.BAD_REQUEST, request);
        } else {
            return handleCustomException(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    private ResponseEntity<ExceptionResponse> handleCustomException(Exception e, HttpStatus status, WebRequest request) {
        Date date = new Date();
        String errorID = Long.toString(date.getTime());
        logger.warn(String.format("ErrorID: %s", errorID) + System.lineSeparator() + e.toString(), e);

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                date,
                status.value(),
                String.format("Please use this ErrorID for support purposes: %s", errorID),
                e.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(exceptionResponse, status);
    }
}