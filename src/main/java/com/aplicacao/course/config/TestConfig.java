package com.aplicacao.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aplicacao.course.entities.User;
import com.aplicacao.course.repositories.UserRepository;


//classe de testes
@Configuration
@Profile("test") //se refere a configuracao da application test
public class TestConfig implements CommandLineRunner {

    //fazendo uma injecao de dependencia
    @Autowired 
    private UserRepository userRepository;

    //metodo implementado pelo command line runner para rodar junto da aplicacao
    @Override
    public void run(String... args) throws Exception{
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        //salva uma lista dentro do banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
    }



}
