package com.aplicacao.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.course.entities.User;
import com.aplicacao.course.services.UserService;

@RestController //rescurso web que é controlado por um recurso REST
@RequestMapping(value = "/users") //caminho do recurso
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping //responde o metodo GET de requisicao
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list); //retornando ok no response para indicar que deu certo e o body para trazer o corpo de requisiçao
    }

    //criando mais um endpoint para buscar o usuario por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
