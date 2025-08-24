package dev.java.ecommerce.basketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//essa anotacao habilita o uso do feign client
//o feign client e uma biblioteca que facilita a chamada de outros servicos http
//ele gera o codigo para fazer as chamadas http de forma mais simples
@EnableFeignClients
@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
