package com.aplicacao.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.course.entities.Order;
import com.aplicacao.course.services.OrderService;

/** 
* Controlador REST responsavel por expor os endpoints relativos aos Pedidos -> Orders
*/
@RestController
@RequestMapping(value = "/orders") //Definindo a rota que irá ter acesso a esse recurso
public class OrderResource {

    //Injeçao de dependencia da classe service para persistir no banco de dados
    @Autowired
    private OrderService service;

    /**
    * Endpoint para buscar todos os pedidos do sistema, retorna uma lista de objetos Order
    */
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();

        //Retorna a resposta com o corpo da lista encontrada
        return ResponseEntity.ok().body(list);
    }

    /**
    * Endpoint para buscar um pedido especifico atraves da URL do seu ID unico,
    * parametro id identificador do produto extraido da URL ex.: '/orders/5'
    */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
