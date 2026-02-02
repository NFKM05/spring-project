package com.aplicacao.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplicacao.course.services.exceptions.DatabaseException;
import com.aplicacao.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


/**
* Manipulador global de exceçoes para a camada de recursos Web
* Essa anotaçao permite interceptar as exceçoes lançadas por qualquer
* controlador da aplicaçao, centralizando o tratamento de erros
*/
@ControllerAdvice 
public class ResourceExceptionHandler {

    /**
    * Intercepta a exceçao personalizada ResourceNotFoundException
    * A exceçao capturada que contem a mensagem de erro
    * Request fornece detalhes da requisiçao HTTP
    * Retorna um objeto StandardError formatado com o status 404 Not Found
    */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; //Erro 404

        //Instancia o padrao de erro customizado com os dados da ocorrencia
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
    * Intercepta a exceçao customizada DatabaseException, disparada em falhas de integridade referencial
    * ao tentar deletar registros com dependencias
    * A exceçao capturada
    * Request fornece detalhes da requisicao HTTP
    * Retorna um objeto StandardError formatado com status HTTP 400
    */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; //o tipo de erro que vai retornar no caso 404
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); //retorna com o status do erro e a mensagem do erro em si 
    }
}
