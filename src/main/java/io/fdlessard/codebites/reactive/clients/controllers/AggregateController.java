package io.fdlessard.codebites.reactive.clients.controllers;

import io.fdlessard.codebites.reactive.clients.model.AggregateCustomer;
import io.fdlessard.codebites.reactive.clients.services.AggregateCustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AggregateController {

    private AggregateCustomerService aggregateCustomerService;

    public AggregateController(AggregateCustomerService aggregateCustomerService) {
        this.aggregateCustomerService = aggregateCustomerService;
    }

    @GetMapping("/{id}")
    public Mono<AggregateCustomer>  getAggregateCustomerById(@PathVariable Long id) {
        return aggregateCustomerService.getAggregateCustomerById(id);
    }
}
