package com.woven.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AssignmentException extends  RuntimeException{
    Exception ex;
    private  ErrorResponse errorResponse;
}
