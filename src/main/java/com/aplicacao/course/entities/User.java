package com.aplicacao.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
* Informa ao JPA que esta classe mapeia uma tabela no banco de dados
* cada instancia de User sera uma linha no banco
*/
@Entity 

/**
* Define o nome propriamente dito da tabela no banco de dados ditando a 
* tabela em que serao salvo estes dados e a que vai fazer associacao as demais
*/
@Table(name = "tb_user")
public class User implements Serializable {

    /*
        --| Basic Entity Checks |--

        Basic Attributes
        Associations (instantiate collections)
        Contructors
        Getters & Setters
        HashCode & Equals
        Serializable

    */


    /**
    * Interface que permite o objeto ser transformado em um fluxo de bytes, essencial
    * para quando o Spring precisa salvar o objeto em sessão, enviar via rede ou gravar
    * em arquivos de cache 
    */
    private static final long serialVersionUID=1L;


    @Id //Definindo o atributo como uma chave primária da tabela
    //Define a estrategia de geraçao de ID, delegando ao banco de dados o incremento ex:. (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Atributos básicos da classe, serão colunas simples no banco de dados
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    /**
    * Interrompe a recursão infinita do JSON, como o User tem uma lista de Orders
    * e cada Order tem um User, sem isso o JSON ficaria chamando User -> Order -> User -> Order
    */
    @JsonIgnore

    /**
    * Define a relação de Um-Para-Muitos (1 User, muitos Pedidos)
    * O comando mappedBy indica que o lado forte, quem possui a FK é o atributo client
    * que está dentro da classe Order 
    */
    @OneToMany(mappedBy = "client")

    /**
    * Sempre instanciar uma coleçao para garantir que se não houver pedidos
    * a lista vai vir vazia de atributos e não nula, o que geraria erro
    */
    private List<Order> orders = new ArrayList<>();


    /*
        --| Constructors |--
    */
    public User(){

    }

    public User(Long id, String name, String email, String phone, String password){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.password=password;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Retorna a lista de pedidos associados a este usuario
    public List<Order> getOrders(){
        return orders;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto User é comparado com outro
        
    */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
