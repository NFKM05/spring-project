package com.aplicacao.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    //usando o metodo post para fazer a criacao do obj
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") //para retornar o 201 created no momento de criacao
        .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //metodo delete usar para remover
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ //esse comando path faz com que o parametro que passar seja uma variavel da url
        service.delete(id);
        return ResponseEntity.noContent().build(); //nao retorna nada
    }

    //metodo put usado para atualizar
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
