package com.demo.weatherapp;

public class Error {
    private int errorCode;
    private int errorMessageId;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorMessageId() {
        return errorMessageId;
    }

    public void setErrorMessageId(int errorMessageId) {
        this.errorMessageId = errorMessageId;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorCode=" + errorCode +
                ", errorMessageId=" + errorMessageId +
                '}';
    }
}
