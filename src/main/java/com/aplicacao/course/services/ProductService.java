package com.aplicacao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.course.entities.Product;
import com.aplicacao.course.repositories.ProductRepository;


    /**
    * Essa anotação registra a classe como um componente do Spring,
    * aqui se coloca as regras de negócio
    */

@Service
public class ProductService {

    //Injeçao de dependência 
    @Autowired
    private ProductRepository repository;

    /**
    * findAll -> Retorna todos os produtos
    * Fluxo Resource >> Service >> Repository >> Banco de Dados >> SELECT *
    */

    public List<Product> findAll(){
        return repository.findAll();
    }

    /**
    * findById -> Busca o produto pelo Id
    * Optional<> é um objeto caixa, que pode conter um produto ou não
    * get() extrai o produto de dentro desta caixa
    */

    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    } 

}
