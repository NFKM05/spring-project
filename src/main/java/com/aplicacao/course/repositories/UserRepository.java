package com.aplicacao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aplicacao.course.entities.User;


/**
* É a camada de acesso a dados, não precisando escrever o código em SQL,
* porque ele faz isso de forma automática durante a execuçao, ao estender
* JpaRepository essa interface vai gerenciar a entidade User que mapeia "tb_user"
* e o Long é o tipo da chave primária dessa entidade
*/
public interface UserRepository extends JpaRepository<User, Long> {

 /**
 * Só de extender a classe de JPA ganha acessa aos métodos
 * save(User) --> Insere um novo usuario ou atualiza se o ID ja existir
 * findAll() --> Retorna todos os usuarios Ex:. select * from tb_user
 * findById(id) --> Busca um usuário pelo Id
 * deleteById(id) --> Remove um usuário do banco de dados
 * count() --> Retorna a quantidade de registros
 */

}
