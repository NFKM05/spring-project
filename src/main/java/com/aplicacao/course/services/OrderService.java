package com.aplicacao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.course.entities.Order;
import com.aplicacao.course.repositories.OrderRepository;
import com.aplicacao.course.services.exceptions.ResourceNotFoundException;

/** 
* Camada de serviço responsavel pelas regras de negocio e intermediaçao entre
* o controlador e o repositorio da entidade Order
* Service registra a classe como um componente de serviço no Spring, permitindo
* a injeçao em outras classes 
*/
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    /**
    * Busca todos os pedidos no banco de dados, contendo objetos do tipo Order
    */
    public List<Order> findAll(){
        return repository.findAll();
    }

    /** 
    * Busca um pedido por ID unico
    * Retorna o objeto Order encontrado
    * Lança exceçao caso o pedido não seja encontrado
    */
    public Order findById(Long id){

        //Se o Optional estiver vazio o ResourceExceptionHandler vai capturar o erro retornando 404
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
