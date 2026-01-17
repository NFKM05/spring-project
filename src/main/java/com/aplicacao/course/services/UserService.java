package com.aplicacao.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.course.entities.User;
import com.aplicacao.course.repositories.UserRepository;

@Service
public class UserService {

    //fazendo injecao de dependencia
    @Autowired
    private UserRepository repository;

    //fazendo um metodo para retornar os usuarios cadastrados no banco de dados
    public List<User> findAll(){
        return repository.findAll();
    }

}
