package com.aplicacao.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


/**
* Entidade que representa as Categorias dos produtos
* Mapeada para a tabela 'tb_category'
*/
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    /**
    * JsonIgnore impede que haja um loop nas chamadas ao consultar categorias
    * ManyToMany relaçao de Muitos para Muitos
    * mappedBy = "categories" mapeamento principal (quem manda na relaçao e define a table auxiliar)
    * no campo 'categories' da classe Product, esta classe Category é o lado passivo da relaçao
    */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    /*
        --| Constructors |--
    */

    public Category(){

    }

    public Category(Long id, String name){
        this.id=id;
        this.name=name;
    }

    /*
        --| Getters and Setters |--
    */

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    
    /**
    * Se usa Set em vez de List para garantir que não haja repetiçao
    * de produtos dentro de uma mesma categoria
    */
    public Set<Product> getProducts() {
        return products;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto Category é comparado com outro
        
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
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
