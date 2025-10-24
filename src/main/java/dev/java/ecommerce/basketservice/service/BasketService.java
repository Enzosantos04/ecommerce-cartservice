package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.entity.Product;
import dev.java.ecommerce.basketservice.enums.Status;
import dev.java.ecommerce.basketservice.repository.BasketRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository, ProductService productService){
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    public Basket createBasket(BasketRequest basketRequest) {
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new RuntimeException("There is already an open basket for this client");
                });
        List<Product> products = getProducts(basketRequest);
        //constroi o objeto do tipo Basket
        //.build() cria o objeto
        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket getBasketById(String id){
        return basketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

    }

    public Basket updateBasket (String basketId, BasketRequest request){
        Basket savedBasket = getBasketById(basketId);

        List<Product> products = getProducts(request);
        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);
    }


    public Basket payBasket(String basketId, PaymentRequest request){
        Basket savedBasket = getBasketById(basketId);
        savedBasket.setPaymentMethod(request.getPaymentMethod());
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);

    }

    public void deleteBasketById(String basketId){
        Basket savedBasket = getBasketById(basketId);
         basketRepository.delete(savedBasket);
    }

    private List<Product> getProducts(BasketRequest basketRequest) {
        List<Product> products = new ArrayList<>();
        //para cada produto na requisição, buscar os detalhes do produto no serviço de produtos
        basketRequest.products().forEach(productRequest ->{
            PlatziProductResponse platziProductResponse = productService.getAllProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        } );
        return products;
    }
}
