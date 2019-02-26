package io.fdlessard.codebites.reactive.clients.gateways;

import io.fdlessard.codebites.reactive.clients.model.Address;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class AddressGatewayImpl implements AddressGateway {

    private WebClient addressWebClient;

    public AddressGatewayImpl(WebClient addressWebClient) {
        this.addressWebClient = addressWebClient;
    }

    public Mono<Address> getAddressById(long id) {

        return addressWebClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Address.class)
                .doOnError(WebClientResponseException.class, e -> handleError(e));
    }

    private void handleError(WebClientResponseException e) {

        throw new GatewayException(e.getResponseBodyAsString(), e.getRawStatusCode());
    }
}
