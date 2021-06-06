package com.woven.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FileSizeException extends RuntimeException{
    public FileSizeException(ErrorResponse errorResponse){
        super("File size Exception");
        this.errorResponse = errorResponse;
    }
    private ErrorResponse errorResponse;
}
