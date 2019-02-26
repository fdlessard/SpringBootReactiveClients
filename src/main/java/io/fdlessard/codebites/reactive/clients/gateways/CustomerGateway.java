package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerGateway {
    Mono<Customer> getCustomerById(long id);
}
