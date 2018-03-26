package com.andriiP.carSaver.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ItemNotFoundException extends RuntimeException {

    private HttpStatus status;

    public ItemNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

}
