package com.aplicacao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplicacao.course.entities.OrderItem;
import com.aplicacao.course.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
