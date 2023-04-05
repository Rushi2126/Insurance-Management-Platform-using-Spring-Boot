package com.rushi.insurance.exceptions;

import org.springframework.validation.BindingResult;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

    private BindingResult result;

    public BadRequestException(String message, BindingResult result) {
        super(message);
        this.result = result;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingResult getResult() {
        return result;
    }

    @Override
    public String getMessage() {
        if (result != null && result.hasErrors()) {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
        return super.getMessage();
    }
}
