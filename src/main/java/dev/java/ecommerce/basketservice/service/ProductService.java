package dev.java.ecommerce.basketservice;


import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final  PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    public List<PlatziProductResponse> getAllProducts() {
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getAllProductById(Long id) {
        return platziStoreClient.getProductById(id);
    }
}
