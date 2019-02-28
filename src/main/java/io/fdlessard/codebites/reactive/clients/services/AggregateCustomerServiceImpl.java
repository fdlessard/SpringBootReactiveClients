package io.fdlessard.codebites.reactive.clients.services;

import io.fdlessard.codebites.reactive.clients.gateways.AddressGateway;
import io.fdlessard.codebites.reactive.clients.gateways.CustomerGateway;
import io.fdlessard.codebites.reactive.clients.gateways.ProductGateway;
import io.fdlessard.codebites.reactive.clients.model.Address;
import io.fdlessard.codebites.reactive.clients.model.AggregateCustomer;
import io.fdlessard.codebites.reactive.clients.model.Customer;
import io.fdlessard.codebites.reactive.clients.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AggregateCustomerServiceImpl implements AggregateCustomerService {

    private CustomerGateway customerGateway;

    private AddressGateway addressGateway;

    private ProductGateway productGateway;

    public AggregateCustomerServiceImpl(
            CustomerGateway customerGateway,
            AddressGateway addressGateway,
            ProductGateway productGateway
    ) {
        this.customerGateway = customerGateway;
        this.addressGateway = addressGateway;
        this.productGateway = productGateway;
    }

    public Mono<AggregateCustomer> getAggregateCustomerById(long id) {

        log.info("AggregateCustomerServiceImpl.getAggregateCustomerById({})", id);

        return customerGateway.getCustomerById(id).flatMap(
                customer -> addressGateway.getAddressById(customer.getAddressId()).flatMap(
                        address -> productGateway.getProductById(customer.getProductId()).flatMap(
                                product -> Mono.just(buildAggregateCustomer(customer, address, product)))));
    }

    private AggregateCustomer buildAggregateCustomer(Customer customer, Address address, Product product) {

        return AggregateCustomer.builder()
                .id(customer.getId())
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .company(customer.getCompany())
                .product(product)
                .address(address)
                .build();
    }
}