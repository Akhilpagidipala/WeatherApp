package com.demo.weatherapp.network;

public class ApplicationException extends RuntimeException {
    private int code;
    private String message;
    private Exception ex;

    public ApplicationException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApplicationException(int code, String message, Exception ex) {
        this.code = code;
        this.message = message;
        this.ex = ex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}
