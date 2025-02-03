package com.digital.wallet.digital_wallet.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import jakarta.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class Error {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private OffsetDateTime timestamp;
    private String message;
    private int code;
    private List<ApiSubError> subErrors;

    private Error() {
        timestamp = OffsetDateTime.now();
    }

    public Error(HttpStatus status) {
        this();
        this.status = status;
    }

    Error(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    Error(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

    Error(HttpStatus status, String message, int code, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.code = code;
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(field, rejectedValue, message));
    }

    private void addValidationError(String message) {
        addSubError(new ApiValidationError(message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getDefaultMessage());
    }

    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }


    // Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }

    abstract class ApiSubError {

    }

    class ApiValidationError extends ApiSubError {
        private String field;
        private Object rejectedValue;
        private String message;
        private int code;

        public ApiValidationError(String field, Object rejectedValue, String message) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
            this.code = 4000;
        }

        ApiValidationError(String message) {
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public void setRejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ApiValidationError that = (ApiValidationError) o;
            return code == that.code && Objects.equals(field, that.field) && Objects.equals(rejectedValue, that.rejectedValue) && Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field, rejectedValue, message, code);
        }
    }
}

