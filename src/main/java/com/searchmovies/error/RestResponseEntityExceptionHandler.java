package com.searchmovies.error;

import com.searchmovies.entity.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorMessage errorMessage;

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(MovieNotFoundException exception,
                                                                    WebRequest request) {
        this.errorMessage.setStatus(HttpStatus.NOT_FOUND);
        this.errorMessage.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
