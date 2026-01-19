package com.aplicacao.course.entities.enums;

public enum OrderStatus {

    //setando um numero para cada tipo, por que no bd se converte em numero
    //fica mais facil para manutencao futura e sem quebra de sequencia
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    //setando um construtor para acessar esses numeros
    private int code;

    private OrderStatus(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }

    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code");
    }

}
