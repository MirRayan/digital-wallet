package com.digital.wallet.digital_wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends IllegalArgumentException {
    int code;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
