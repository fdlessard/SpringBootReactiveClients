package io.fdlessard.codebites.reactive.clients.services;

import io.fdlessard.codebites.reactive.clients.gateways.AddressGateway;
import io.fdlessard.codebites.reactive.clients.gateways.CustomerGateway;
import io.fdlessard.codebites.reactive.clients.gateways.ProductGateway;
import io.fdlessard.codebites.reactive.clients.model.Address;
import io.fdlessard.codebites.reactive.clients.model.AggregateCustomer;
import io.fdlessard.codebites.reactive.clients.model.Customer;
import io.fdlessard.codebites.reactive.clients.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

        Mono<Customer> customerMono = customerGateway.getCustomerById(id);
        Mono<Address> addressMono = addressGateway.getAddressById(id);
        Mono<Product> productMono = productGateway.getProductById(id);


        customerMono.then(addressGateway.getAddressById())

        return Mono.zip(customerMono, addressMono)
                .map(t -> buildAggregateCustomer(t.getT1(), t.getT2()));


                // https://stackoverflow.com/questions/48172582/is-it-possible-to-start-monos-in-parallel-and-aggregate-the-result
//        return Mono.just(AggregateCustomer.builder().build());
    }

    private AggregateCustomer buildAggregateCustomer(Customer customer, Address address, Product product) {
        return AggregateCustomer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .company(customer.getCompany())
                .address(address)
                .build();
    }

}
