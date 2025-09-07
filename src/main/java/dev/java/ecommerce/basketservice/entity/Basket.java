package dev.java.ecommerce.basketservice.entity;

import dev.java.ecommerce.basketservice.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "basket") // usar document quando estiver utilizando mongodb
public class Basket {
    @Id
    private String id; // no mongodb o id eh uma string
    private long client;
    private BigDecimal totalPrice;
    private List<Product> products;
    private Status status;

    // metodo para calcular o total do carrinho
    //pega o preço de cada produto e multiplica pela quantidade
    //e soma tudo
    public void calculateTotalPrice(){
        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
