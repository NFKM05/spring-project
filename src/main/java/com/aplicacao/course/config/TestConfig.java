package com.aplicacao.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aplicacao.course.entities.Category;
import com.aplicacao.course.entities.Order;
import com.aplicacao.course.entities.OrderItem;
import com.aplicacao.course.entities.Payment;
import com.aplicacao.course.entities.Product;
import com.aplicacao.course.entities.User;
import com.aplicacao.course.entities.enums.OrderStatus;
import com.aplicacao.course.repositories.CategoryRepository;
import com.aplicacao.course.repositories.OrderItemRepository;
import com.aplicacao.course.repositories.OrderRepository;
import com.aplicacao.course.repositories.ProductRepository;
import com.aplicacao.course.repositories.UserRepository;


//Aqui faço todos os testes em ambiente de homologaçao

//Essa anotation indica que é uma classe de configuração
@Configuration

/**
* Anotation especifica que sera ativa quando o perfil de teste estiver selecionado no 'application.properties'
* Isso evita que dados sejam inseridos no seu banco de dados em produçao por acidente
*/
@Profile("test")
public class TestConfig implements CommandLineRunner {

    /**
    * Autowired mecanismo de injecao de dependencia, o Spring cria automaticamente as instancias
    * dessas interfaces Repositories e as injeta sem precisar dar o comando new
    */
    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    //Metodo implementado pelo command line runner para rodar junto da aplicacao
    @Override
    public void run(String... args) throws Exception{

        //Instanciando categorias nas tabelas
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        //Instanciando produtos nas tabelas
        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        //Persistindo categorias e produtos iniciais 
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));


        /**
        * Associacao de muitos para muitos Many-To-Many, adicionando categorias aos produtos,
        * ao salvar os produtos novamente, ele criara os registros na tabela auxiliar
        */
        p1.getCategories().add(cat2); //Basicamente pega o objeto Product e adiciona ele a uma categoria
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        //Salva os produtos novamente agora com as associacoes de categoria mapeadas
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        //Intanciando os usuários
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        /**
        * Instanciando pedidos associacao de Muitos-Para-Um, cada pedido recebe um objeto User no construtor
        * isso influencia a FK no banco, Instant.parse segue o ISO 8601 (UTC)
        */ 
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        //Salva os usuarios e depois os pedidos
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        /**
        * Itens de pedido, tabela de associacao com chave composta,
        * OrderItem associa a um Order a um Produto, isso preenche
        * a classe OrderItemPK de forma transparente 
        */
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        //Pagamento associacao de Um-Para-Um, para salvar o pagamento primeiro criamos o objeto Payment
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);

        /**
        * Depois associamos o pagamento ao pedido e mandamos o OrderRepository salvar o pedido
        * O JPA ira persistir o pagamento devido ao mapeamento na classe Order
        */
        o1.setPayment(pay1);
        orderRepository.save(o1);

    }
}
