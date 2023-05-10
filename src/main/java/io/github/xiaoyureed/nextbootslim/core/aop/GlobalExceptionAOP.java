package io.github.xiaoyureed.nextbootslim.core.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.xiaoyureed.nextbootslim.core.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAOP {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<?>> handleError(Exception exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<ResponseWrapper<?>>(
                ResponseWrapper.error(exception.getMessage()),
                HttpStatus.OK);
    }
}
