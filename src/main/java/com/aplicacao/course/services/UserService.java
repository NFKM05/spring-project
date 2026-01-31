package com.aplicacao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aplicacao.course.entities.User;
import com.aplicacao.course.repositories.UserRepository;
import com.aplicacao.course.services.exceptions.DatabaseException;
import com.aplicacao.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


/**
* Registra a classe como um componente de serviço no Spring, permitindo que a classe
* seja injetada em outras classes como resource, é nesta camada que as regras de negócio
* e o controle de transaçoes devem ficar  
*/
@Service
public class UserService {

    //Injeçao de dependencia do Repository, para conversar efetivamente com o banco de dados
    @Autowired
    private UserRepository repository;

    /**
    * findAll --> Retorna todos os usuários
    * Chama o método padrão do JpaRepository que executa um SELECT * FROM tb_user
    */
    public List<User> findAll(){
        return repository.findAll();
    }

    /**
    * findById --> Busca um usuário em especifico
    * Optional<User> --> Objeto container pode ou não conter um User
    * Ajuda a evitar o NullPointerException
    * .orElseThrow --> Se o User existir, retorna ele, se não existir lança a exceção personalizada
    */
    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //para lançar a excecao no metodo se nao encontrar o id
    }

    /**
    * insert --> Salva um novo usuário no banco
    * repository.save(obj) --> Retorna um objeto já com o ID preenchido pelo banco
    */
    public User insert(User obj){
        return repository.save(obj);
    }

    /**
    * delete --> Remove um registro pelo ID
    * EmptyResultDataAccessException --> Captura o erro se tentarmos deletar um ID inexistente
    * DataIntegrityViolationException --> Captura erro se tentar deletar um User que possui pedidos associados
    * quebrando a integridade do banco de dados
    */
    public void delete(Long id){
        try{ 
        repository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id); //trata do erro quando nao encontra o mesmo 
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }


    /**
    * update --> Atualiza um registro existente
    * getReferenceById(id) --> Ele prepara um objeto monitorado Proxy pelo JPA, muito mais eficiente
    * para atualizações, pois só acessa o banco no momento do save
    */
    public User update(Long id, User obj){
        try { 
        User entity = repository.getReferenceById(id); //Instancia o usuário monitorado
        updateData(entity, obj); //Atualiza os dados da entidade com os novos dados do objeto
        return repository.save(entity); //Salva as mudanças
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    /**
    * updateData --> Metodo auxiliar privado, não exposto fora da classe
    * Controlando quais campos devem ser atualizados
    */
    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
