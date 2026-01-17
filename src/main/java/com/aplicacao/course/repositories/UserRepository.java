package com.aplicacao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aplicacao.course.entities.User;

//passa a classe e o tipo da chave que vamos usar
public interface UserRepository extends JpaRepository<User, Long> {

    
}
