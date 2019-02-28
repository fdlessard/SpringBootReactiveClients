package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductGatewayImpl implements ProductGateway {

    private WebClient productWebClient;

    public ProductGatewayImpl(WebClient productWebClient) {
        this.productWebClient = productWebClient;
    }

    public Mono<Product> getProductById(long id) {

        log.info("ProductGatewayImpl.getProductById({})", id);

        return productWebClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(WebClientResponseException.class, e -> handleError(e));
    }

    private void handleError(WebClientResponseException e) {

        throw new GatewayException(e.getResponseBodyAsString(), e.getRawStatusCode());
    }
}
