package com.aplicacao.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplicacao.course.services.exceptions.DatabaseException;
import com.aplicacao.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //intercepta as exceçoes que ocorrem para esse objeto executar um possivel tratamento
public class ResourceExceptionHandler {

    //colocar o nome da excecao que vou estar interceptando
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; //o tipo de erro que vai retornar no caso 404
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); //retorna com o status do erro e a mensagem do erro em si 
    }

    //excecao para caso for deletar um usuario e ele tiver pedidos associados no banco de dados
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; //o tipo de erro que vai retornar no caso 404
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); //retorna com o status do erro e a mensagem do erro em si 
    }

}
