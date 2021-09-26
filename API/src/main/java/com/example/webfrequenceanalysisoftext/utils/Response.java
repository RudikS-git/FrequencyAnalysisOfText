package com.example.webfrequenceanalysisoftext.utils;

public class Response {

    private String errorMessage;
    private Boolean success = false;

    public Response() {
    }

    public Response(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
