package com.aplicacao.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.course.entities.Category;
import com.aplicacao.course.services.CategoryService;


/**
* Controlador REST que gerencia as categorias dos produtos,
* fornece endpoints para listagem e busca individual de categorias
*/
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    //Injecao de dependencia representando a camada de serviço
    @Autowired
    private CategoryService service;

    /** 
    * Recupera todas as categorias cadastradas no banco de dados,
    * contendo uma lista de objetos Category
    */
    @GetMapping
    private ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /** 
    * Busca a categoria pelo seu identificador ID único
    */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
