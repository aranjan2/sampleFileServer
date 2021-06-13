package com.woven.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileNotFound extends RuntimeException {
  private ErrorResponse errorResponse;
}

