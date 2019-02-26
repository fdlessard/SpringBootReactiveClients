package io.fdlessard.codebites.reactive.clients.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AggregateCustomer {

    private Long id;
    private String lastName;
    private String firstName;
    private String company;
    private Address address;
    private Product product;
}
