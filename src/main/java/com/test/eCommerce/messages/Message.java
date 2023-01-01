package com.test.eCommerce.messages;

import org.springframework.http.HttpStatus;

public class Message {


    private String message;
    private HttpStatus status;

    private Object object;

    public Message(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public Message(String message, HttpStatus status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
