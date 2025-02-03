package com.digital.wallet.digital_wallet.exception;

import org.springframework.http.HttpStatus;

public class SiteException extends Exception {
    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public SiteException(String message) {
        super(message);
    }

    public SiteException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
