package com.aplicacao.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.course.entities.Product;
import com.aplicacao.course.services.ProductService;

//Define que esta classe é um controlador REST, convertendo os objetos para JSON
@RestController

//Define a rota base, no caso o caminho da API REST para ser acessada os métodos
@RequestMapping(value = "/products")
public class ProductResource {

    //Injeçao de dependência, entregando a instancia de ProductService aqui
    @Autowired
    private ProductService service;

    //Mapeia as requisições HTTP GET para buscar os dados conforme a funçao findAll()
    @GetMapping

    //No corpo de requisiçao é passado uma lista do tipo Product contendo os objetos de produtos
    public ResponseEntity<List<Product>> findAll(){

        //Chamando o serviço com a dependencia da classe Service para localizar os objetos
        List<Product> list = service.findAll();

        //ok() Retorna o status HTTP 200 e body() coloca a lista de produtos convertida em JSON no corpo da resposta
        return ResponseEntity.ok().body(list);
    }


    //Metodo GET e o caminho entre as váriaveis define mais uma rota para ser acessada
    @GetMapping(value = "/{id}")

    /**
    * Aqui ele retorna dentro do escopo somente o produto, que é localizado pelo Id,
    * passando a variavel como path variable para referenciar a busca
    */
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
