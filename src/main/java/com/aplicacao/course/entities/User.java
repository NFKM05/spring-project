package com.aplicacao.course.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //declarando que é uma entidade persistente
@Table(name = "tb_user") //declarando o nome da tabela no banco de dados
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


    //para transformar o obj em cadeias de bytes para trafegar em redes e ser salvo em arquivos
    private static final long serialVersionUID=1L;


    @Id //definindo que o id vai ser uma chave primaria 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //isso define que é auto increment no banco de dados
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User(){

    }

    public User(Long id, String name, String email, String phone, String password){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.password=password;
    }

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
