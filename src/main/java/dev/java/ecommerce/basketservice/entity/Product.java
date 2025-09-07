package dev.java.ecommerce.basketservice.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


public class Products {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
