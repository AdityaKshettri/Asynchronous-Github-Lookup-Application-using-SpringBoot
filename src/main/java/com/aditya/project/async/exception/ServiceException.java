package com.aditya.project.async.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorCatalog errorCatalog;

    public ServiceException(ErrorCatalog errorCatalog) {
        this(errorCatalog, errorCatalog.getMessage(), null);
    }

    public ServiceException(ErrorCatalog errorCatalog, Throwable cause) {
        this(errorCatalog, errorCatalog.getMessage(), cause);
    }

    public ServiceException(ErrorCatalog errorCatalog, String message) {
        this(errorCatalog, message, null);
    }

    public ServiceException(ErrorCatalog errorCatalog, String message, Throwable cause) {
        super(message, cause);
        this.errorCatalog = errorCatalog;
    }
}
