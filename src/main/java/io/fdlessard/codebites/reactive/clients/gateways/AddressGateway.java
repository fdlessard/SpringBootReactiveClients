package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Address;
import reactor.core.publisher.Mono;

public interface AddressGateway {

    Mono<Address> getAddressById(long id);
}
