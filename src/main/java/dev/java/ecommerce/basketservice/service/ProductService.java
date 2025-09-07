package dev.java.ecommerce.basketservice.service;


import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final  PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    @Cacheable(value ="products")
    public List<PlatziProductResponse> getAllProducts() {
        return platziStoreClient.getAllProducts();
    }


    @Cacheable(value = "products", key = "#productId")
    public PlatziProductResponse getAllProductById(Long productId) {
        log.info("Fetching product with id {}", productId);
        return platziStoreClient.getProductById(productId);
    }
}
