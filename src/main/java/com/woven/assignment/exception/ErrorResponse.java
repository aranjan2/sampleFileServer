package com.woven.assignment.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
