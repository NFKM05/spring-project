package com.aplicacao.course.entities.pk;

import java.io.Serializable;


import com.aplicacao.course.entities.Order;
import com.aplicacao.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


/**
* Embeddable infica que esta classe é embutivel, nao possui table propria mas
* seus campos farão parte de outra entidade, neste caso compondo a PK de OrderItem
*/
@Embeddable
public class OrderItemPK implements Serializable {

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
    * ManyToOne no contexto de uma chave composta, indica que parte do ID é uma
    * referência para outra entidade Order
    * JoinColumn nome da coluna de chave estrangeira que será criada na tabela tb_order_item
    */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Identifica o produto que faz parte dessa chave composta
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /*
        --| Getters and Setters |--
    */

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto OrderItemPK é comparado com outro
        
    */
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        OrderItemPK other = (OrderItemPK) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
}
