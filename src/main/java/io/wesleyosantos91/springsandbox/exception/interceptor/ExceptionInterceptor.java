package io.wesleyosantos91.springsandbox.exception.interceptor;

import io.wesleyosantos91.springsandbox.exception.core.FallbackMethodException;
import io.wesleyosantos91.springsandbox.exception.core.ObjectNotFoundException;
import io.wesleyosantos91.springsandbox.model.response.ResponseErro;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ExceptionInterceptor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ResponseErro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseErro> handlerPessoaNotFoundException(ObjectNotFoundException e) {

        String erro = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseErro(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        erro,
                        e.getMessage(),
                        OffsetDateTime.now()));

    }

    @ExceptionHandler(FallbackMethodException.class)
    public ResponseEntity<ResponseErro> handlerFallbackMethodException(FallbackMethodException e) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseErro(HttpStatus.SERVICE_UNAVAILABLE.value(),
                        HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                        e.getMessage(), //TODO criar uma mensagem amigavel
                        e.getMessage(),
                        OffsetDateTime.now()));

    }

    private List<ResponseErro> criarListaDeErros(BindingResult bindingResult) {
        List<ResponseErro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String erro = fieldError.toString();
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            erros.add(new ResponseErro(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    erro, mensagem, OffsetDateTime.now()));
        }

        return erros;
    }
}
