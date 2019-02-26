package io.fdlessard.codebites.reactive.clients.gateways;

import lombok.Getter;

@Getter
public class GatewayException extends RuntimeException {

    private int status;

    public GatewayException() {
        super();
    }

    public GatewayException(String message, int status) {
        super(message);
        this.status = status;
    }

}
