package com.aplicacao.course.services.exceptions;


/** 
* Exceçao personalizada disparada quando um recurso solicitado não é encontrado no banco de dados,
* utilizado normalmente em operaçoes de busca ou tentar atualizar/deletar algum registro
*/
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID=1L;

    /** 
    * Construtor que recebe o ID do recurso para montar uma mensagem de erro clara
    */
    public ResourceNotFoundException(Object id){

        //Passa a mensagem formatada para a superclasse RuntimeException
        super("Resource not found Id " + id);
    }

}
