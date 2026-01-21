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

@Service
public class UserService {

    //fazendo injecao de dependencia
    @Autowired
    private UserRepository repository;

    //fazendo um metodo para retornar os usuarios cadastrados no banco de dados
    public List<User> findAll(){
        return repository.findAll();
    }

    //metodo para buscar um usuario por id
    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //para lançar a excecao no metodo se nao encontrar o id
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{ 
        repository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id); //trata do erro quando nao encontra o mesmo 
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        try { 
        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    //define os campos que vao ser atualizados
    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
