package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class CustomerGatewayImpl implements CustomerGateway {

    private WebClient customerWebClient;

    public CustomerGatewayImpl(WebClient customerWebClient) {
        this.customerWebClient = customerWebClient;
    }

    public Mono<Customer> getCustomerById(long id) {
        return customerWebClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Customer.class)
                .doOnError(WebClientResponseException.class, e -> handleError(e));
    }

    private void handleError(WebClientResponseException e) {

        throw new GatewayException(e.getResponseBodyAsString(), e.getRawStatusCode());
    }
}
