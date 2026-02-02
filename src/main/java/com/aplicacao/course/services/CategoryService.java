package com.aplicacao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.course.entities.Category;
import com.aplicacao.course.repositories.CategoryRepository;
import com.aplicacao.course.services.exceptions.ResourceNotFoundException;

/**
* Camada de serviço responsavel por gerenciar as operaçoes de negocio,
* permite que o Spring gerencie o ciclo de vida desta classe 
*/
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Retorna todas as categorias cadastradas no sistema, retornando lista de Category
    public List<Category> findAll(){
        return repository.findAll();
    }

    //Busca uma categoria especifica por seu ID
    public Category findById(Long id){

        //Tenta obter o objeto dentro do Optional, caso esteja vazio lança exceçao
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
