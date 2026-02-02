package com.aplicacao.course.entities;

import java.io.Serializable;

import com.aplicacao.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
* Classe associativa que representa os itens de um pedido,
* associando um Pedido a um Produto, adicionando quantidade e preço
*/
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    /*
        --| Basic Entity Checks |--

        Basic Attributes
        Associations (instantiate collections)
        Contructors
        Getters & Setters
        HashCode & Equals
        Serializable

    */

    private static final long serialVersionUID=1L;

    /**
    * EmbeddedId indica que o identificador desta classe é uma chave composta
    * que esta embutida em outra classe OrderItemPK, fundamental instanciar o objeto
    * new OrderItemPK para nao dar NullPointerException
    */
    @EmbeddedId 
    private OrderItemPK id = new OrderItemPK(); //quando tem id da classe associativa precisa startar com o obj
    private Integer quantity;
    private Double price;

    /*
        --| Constructors |--
    */

    public OrderItem(){

    }

    /**
    * Construtor customizado, sem passar o id composto diretamente
    * mas sim os objetos Order e Product, que sao atribuidos ao Id interno
    */
    public OrderItem(Order order, Product product, Integer quantity, Double price){
        id.setOrder(order);
        id.setProduct(product);
        this.quantity=quantity;
        this.price=price;
    }

    /*
        --| Getters and Setters |--
    */

    /**
    * JsonIgnore Importante no GET para evitar que o JSON tente renderizar
    * o pedido dentro do item, causando um loop de chamadas order >> item >> order
    */
    @JsonIgnore 
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        id.setOrder(order);
    }

    //Sem a anotation JsonIgnore, pois queremos ver qual produto é o item
    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
    * Essencial para o calculo do valor total do pedido
    * O Spring/Jackson reconhecerá isso como um campo no JSON 'subtotal'
    */
    public Double getSubTotal(){
        return price * quantity;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto OrderItem é comparado com outro
        
    */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
