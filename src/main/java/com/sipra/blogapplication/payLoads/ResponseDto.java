package com.sipra.blogapplication.payLoads;

import java.util.List;

public class ResponseDto {
    private boolean success;
    private Object data;
    private String message;

    public ResponseDto(){
    }

    public ResponseDto(boolean success, Object data, String message ) {
        this.message = message;
        this.data = data;
        this.success = success;
    }
    public ResponseDto(boolean success, String message) {
        this.message = message;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
