package io.wesleyosantos91.springsandbox.exception.interceptor;

import io.wesleyosantos91.springsandbox.exception.core.FallbackMethodException;
import io.wesleyosantos91.springsandbox.exception.core.ObjectNotFoundException;
import io.wesleyosantos91.springsandbox.model.response.ResponseErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseErro> handlerPessoaNotFoundException(ObjectNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseErro(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        e.getMessage(),
                        OffsetDateTime.now()));

    }

    @ExceptionHandler(FallbackMethodException.class)
    public ResponseEntity<ResponseErro> handlerFallbackMethodException(FallbackMethodException e) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseErro(HttpStatus.SERVICE_UNAVAILABLE.value(),
                        HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                        e.getMessage(),
                        OffsetDateTime.now()));

    }
}
