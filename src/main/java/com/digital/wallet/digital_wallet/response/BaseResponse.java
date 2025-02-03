package com.digital.wallet.digital_wallet.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    @JsonProperty("data")
    Object object;

    @JsonProperty("code")
    int code;

    @JsonProperty("error")
    ErrorResponse errorResponse;

    public BaseResponse() {
    }

    public BaseResponse(Object object, int code, ErrorResponse errorResponse) {
        this.object = object;
        this.code = code;
        this.errorResponse = errorResponse;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
