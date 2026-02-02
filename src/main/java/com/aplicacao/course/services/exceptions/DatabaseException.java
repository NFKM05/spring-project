package com.aplicacao.course.services.exceptions;


/** 
* Exceçao personalizada para representar erros especificos em operaçoes com o banco de dados,
* geralmente lançada quando ocorre um erro na violaçao de integridade, extende Runtime para que
* nao seja obrigatorio o tratamento
*/
public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID=1L;

    //Construtor com mensagem personalizada da superclasse
    public DatabaseException(String msg){
        super(msg);
    }

}
