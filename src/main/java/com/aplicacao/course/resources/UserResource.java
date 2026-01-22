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

//Define que essa classe é um controlador REST, convertendo automaticamente respostas Obj Java para JSON 
@RestController 

//Define a rota base, qualquer requisicao no caminho http://localhost:8080/users caira nesta classe
@RequestMapping(value = "/users") 
public class UserResource {

    //Injeta a camada de servico, o Resource nunca acessa o banco "Repository" diretamente ele delega para o User Service
    @Autowired
    private UserService service;

    //Mapeia requisiçoes HTTP GET, usado para buscar dados
    @GetMapping 
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();

        // .ok() --> Retorna o código HTTP 200 Sucesso
        // .body() --> Coloca a lista de usuários no corpo do JSON de resposta
        return ResponseEntity.ok().body(list); 
    }

    //Define uma variavel do caminho na URL Ex:. /users/5 
    @GetMapping(value = "/{id}")

    //Path Variable vincula o Id que vem na URL ao parâmetro Long Id do metodo
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //Mapeia requisiçoes HTTP POST, usado para criar novos recursos
    @PostMapping

    //Request Body deserializa o JSON no corpo da requisiçao para um novo objeto
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);

        /**
        * Boas práticas REST --> Ao criar um recurso devemos retornar o status 201 Created, e um cabeçalho chamado
        * Location contendo a URL do novo recurso criado ServletUriComponentsBuilder cria essa URI de forma dinâmica
        * baseada na requisiçao atual
        */
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();

        // .created(uri) retorna o 201 e o cabeçalho Location
        return ResponseEntity.created(uri).body(obj);
    }

    //Mapeia requisiçoes HTTP DELETE, usado para remover dados 
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        // .noContent() retorna o código 204, status padrão quando ocorre com sucesso mas não retorna nada
        return ResponseEntity.noContent().build();
    }

    //Mapeia requisiçoes HTTP PUT, usado para atualizar dados existentes 
    @PutMapping(value = "/{id}")

    //Aqui se usa tanto Path Variable para saber QUEM atualizar, e Request Body para saber o QUE atualizar
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
