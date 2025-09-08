package dev.java.ecommerce.basketservice.repository;

import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
//mongo repository faz abstrcao das queries do banco de dados
//tendo os metodos basicos de CRUD
public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, Status status) ;
}
