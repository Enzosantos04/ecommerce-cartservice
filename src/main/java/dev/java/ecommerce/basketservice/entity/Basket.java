package dev.java.ecommerce.basketservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "basket") // usar document quando estiver utilizando mongodb
public class Basket {
    private String id; // no mongodb o id eh uma string
    private long client;
    private BigDecimal totalPrice;

}
