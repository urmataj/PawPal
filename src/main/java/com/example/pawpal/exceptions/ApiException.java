package com.example.pawpal.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatusCode statusCode;

    public ApiException(String message, HttpStatusCode statusCode) {
      super(message);
      this.statusCode = statusCode;
    }
}
