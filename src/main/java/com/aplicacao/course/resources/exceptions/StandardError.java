package com.aplicacao.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
* Classe utilitaria que define a estrutura padrão para respostas de erro da API,
* garante que todos os erros retornados ao cliente sigam neste mesmo formato, facilitando
* o tratamento de exceçoes do cliente
*/
public class StandardError implements Serializable {

    private static final long serialVersionUID=1L;

    //Momento exato em que o erro ocorre, formatado para o padrão ISO 8601 UTC
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;

    //Codigo de status HTTP
    private Integer status;

    //Descriçao curta do tipo de erro
    private String error;

    //Mensagem detalhada sobre a causa do erro
    private String message;

    //O caminho da URL (URI) onde a exceçao foi disparada
    private String path;

    //Construtor padrão da classe
    public StandardError(){
    }

    /**
    * Construtor completo para instanciar um erro com todas as informaçoes necessarias
    * timestamp -> Momento da ocorrencia
    * status -> Código HTTP
    * error -> Descriçao do erro
    * message -> Mensagem detalhada
    * path -> Caminho da requisiçao
    */
    public StandardError(Instant timestamp, Integer status, String error, String message, String path){
        this.timestamp=timestamp;
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }

    //Getters & Setters

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
