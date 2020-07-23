package com.xz.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handle(WebExchangeBindException e) {
        log.info("handle WebExchangeBindException");
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    private String toStr(WebExchangeBindException e) {
        return e.getFieldErrors().stream().map(fieldError ->
                fieldError.getField() + ":" + fieldError.getDefaultMessage()
        ).reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}
