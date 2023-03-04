package com.backend.demospringboot.Error;

public class DepartmemtNotFoundException extends Exception{
    public DepartmemtNotFoundException() {
        super();
    }

    public DepartmemtNotFoundException(String message) {
        super(message);
    }

    public DepartmemtNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmemtNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DepartmemtNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
