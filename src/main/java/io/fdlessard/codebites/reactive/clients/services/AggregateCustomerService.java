package io.fdlessard.codebites.reactive.clients.services;


import io.fdlessard.codebites.reactive.clients.model.AggregateCustomer;
import reactor.core.publisher.Mono;

public interface AggregateCustomerService {

    Mono<AggregateCustomer> getAggregateCustomerById(long id);
}
