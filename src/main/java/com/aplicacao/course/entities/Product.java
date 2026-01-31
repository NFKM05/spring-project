
package com.aplicacao.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
* Anotation Entity identifica a classe como tabela no banco de dados
* Table nomeia a tabela dentro do banco de dados
*/

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    /**
    * ID -> Identifica a chave primária da tabela no banco de dados
    * GeneratedValue -> Identifica a regra que vai ser atribuida ao banco para incrementar o ID
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    
    /**
    * ManyToMany -> Define que muitos produtos podem pertencer a muitas categorias
    * JoinTable -> Esse comando cria a tabela de junçao, a tabela associativa entre dois elementos
    * no Banco de Dados (tb_product_category)
    * 
    * JoinColumns -> Define a chave estrangeira do outro objeto product_id
    * inverseJoinColumns -> Define a chave estrangeira do outro objeto category_id
    */

    @ManyToMany
    @JoinTable(name = "tb_product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))

    /**
    * Usando Set ao invés de List por que o mesmo não admite ter elementos repetidos,
    * evita que um produto seja inserido em mesma categoria por erro de lógica
    */
    private Set<Category> categories = new HashSet<>();


    /**
    * OneToMany -> Um produto pode estar presente em vários itens de pedido
    * mappedBy -> id.product dentro da classe OrderItem existe um atributo id
    * chave composta da OrderItemPK e dentro deste id que está o atributo product
    */

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    /*
         --| Constructors |--
    */

    public Product(){

    }

    public Product(Long id, String name, String description, Double price, String imgUrl){
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.imgUrl=imgUrl;
    }

    /*
        --| Getters & Setters |--
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    //Retorna as categorias associadas a este produto
    public Set<Category> getCategories() {
        return categories;
    }

    /**
    * JsonIgnore -> Importante para evitar o loop infinito no JSON
    * getOrders() -> Método que implementa uma lógica na classe para descobrir
    * quais pedidos Order este produto está incluído
    */

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for(OrderItem x : items){ 
            set.add(x.getOrder());
        }
        return set;
    }

    /*
        --| HashCode & Equals |--
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
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
