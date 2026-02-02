package com.aplicacao.course.entities.enums;

/**
* Enumeraçao representa os possiveis status de um pedido no sistema,
* o controle é feito atraves de codigos numericos manuais para garantir
* a consistência dos dados no banco de dados prevenindo manutençoes futuras
*/
public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    //setando um construtor para acessar esses numeros
    private int code;

    /**
    * Construtor privado para associar um código numerico a um status
    */
    private OrderStatus(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }

    /**
    * Converte um codigo numerico inteiro para o respectivo objeto OrderStatus,
    * passa de parametro o codigo numerico e retorna, o objeto Enum correspondente
    * ao codigo informado, ele lança uma exceçao caso der erro
    */
    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code");
    }

}
