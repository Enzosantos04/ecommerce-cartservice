package dev.java.ecommerce.basketservice.controller;

import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService){
        this.basketService = basketService;

    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(request));

    }

    @GetMapping("/{id}")
    ResponseEntity<Basket> getBasketById(@PathVariable String id){
        return ResponseEntity.ok(basketService.getBasketById(id));

    }

}
