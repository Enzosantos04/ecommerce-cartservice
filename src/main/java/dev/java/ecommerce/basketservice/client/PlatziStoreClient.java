package dev.java.ecommerce.basketservice.client;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//anotacao do feign client para fazer chamadas http para outros servicos
//nome do cliente e url do servico
//url do servico vem do application.yml
@FeignClient(name = "platzi", url = "${basket.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
     PlatziProductResponse getProductById(@PathVariable Long id);
}
