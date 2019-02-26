package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Product;
import reactor.core.publisher.Mono;

public interface ProductGateway {

    Mono<Product> getProductById(long id);
}
