package dev.java.ecommerce.basketservice.controller;

import dev.java.ecommerce.basketservice.service.ProductService;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts() {
        List<PlatziProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getAllProductById(@PathVariable Long id) {
        PlatziProductResponse product = productService.getAllProductById(id);
        return ResponseEntity.ok(product);
    }
}
