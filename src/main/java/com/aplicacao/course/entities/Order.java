package com.aplicacao.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.aplicacao.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "tb_order")
public class Order implements Serializable {

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

    /**
    * Definindo o atributo que vai ser a chave primária da tabela, e também
    * a regra que ele usará para se incrementar
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * JsonFormat -> Controla como o Jackson serializador JSON formata a data
    * O shape garante que a data vire um texto no JSON
    * O pattern segue o padrão ISO 8601
    * O timezone faz com que o horário siga o padrão como horário de Greenwich UTC
    */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    //Setando como inteiro para auxiliar na refatoração futuramente dos códigos no banco de dados
    private Integer orderStatus;

    /** 
    * Relaçao de Muitos-Para-Um, muitos pedidos pertencem a um cliente
    * JoinColumn -> Responsavel por criar a chave estrangeira FK fisica na tabela 'tb_order'
    * diferente do mappedBy este é o lado forte da relaçao que guarda o Id do dono
    */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    /**
    * OneToMany -> Um para muitos, um pedido possui vários itens, o mappedBy indica
    * que o mapeamento é feito pela chave composta id na classe OrderItem acessando
    * o campo 'order' dentro dela
    */
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    /**
    * OneToOne -> Relaçao de Um para Um, um pedido tem exatamente um pagamento
    * mappedBy = "order" indica que a FK esta na tabela payment
    * cascade = CascadeType.ALL garante que se o pedido for deletado o pagamento também
    */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    /*
        --| Constructors |--
    */

    public Order(){

    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client){
        this.id=id;
        this.moment=moment;
        setOrderStatus(orderStatus);
        this.client=client;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    //Converte o valor Integer salvo no banco de volta para Enum
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    //Converte o tipo Enum recebido para Integer antes de salvar no banco
    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){ 
        this.orderStatus = orderStatus.getCode();
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    /**
    * Regra de negocio -> Calcula o valor total do pedido somando os subtotais dos itens
    * o prefixo 'get' faz com que o Jackson inclua o campo 'total' no JSON final automaticamente
    */
    public Double getTotal(){
        double sum = 0.0;
        for(OrderItem x : items){
            sum += x.getSubTotal();
        }
        return sum;
    }

    /*
        --| HashCode and Equals |--

        -> Define como o objeto Order é comparado com outro
        
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
