package com.aplicacao.course.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
* Entidade que representa o Pagamento
Em um modelo de dominio o pagamento é uma extensão do pedido
*/

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

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

    //Id e GeneratedValue -> Identificador unico do pagamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    /**
    * JsonIgnore impede que o objeto seja serializado de volta para JSON
    * evitando o erro de recursão infinita, ficando em loop de vez pedido >> pagamento >> pedido
    * OneToOne relaçao Um para Um, um pagamento pertence a um unico pedido
    * MapsId anotação chave, faz com que o Id do Payment seja o mesmo Id do Order relacionado
    * ou seja, nao existira um 'id 5' para Payment se o Order foi 'id 10'
    */
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    /*
        --| Constructors |--
    */

    public Payment(){

    }

    public Payment(Long id, Instant moment, Order order){
        this.id=id;
        this.moment=moment;
        this.order=order;
    }

    /*
        --| Getters and Setters |--
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto Payment é comparado com outro
        
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
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
