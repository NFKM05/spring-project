package com.aplicacao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplicacao.course.entities.Product;

/**
* É a camada de acesso a dados, não precisando escrever o código em SQL,
* porque ele faz isso de forma automática durante a execuçao, ao estender
* JpaRepository essa interface vai gerenciar a entidade User que mapeia "tb_user"
* e o Long é o tipo da chave primária dessa entidade
*/
public interface ProductRepository extends JpaRepository<Product, Long>{

}
