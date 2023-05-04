package io.github.xiaoyureed.javademo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.xiaoyureed.javademo.base.Response;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAOP {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleError(Exception exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<Response<?>>(
                Response.error(exception.getMessage()),
                HttpStatus.OK);
    }
}
