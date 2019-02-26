package io.fdlessard.codebites.reactive.clients.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class Configurations {


    public final static String ADDRESS_BASE_URL = "http://127.0.0.1:8080/addresses";

    public final static String CUSTOMER_BASE_URL = "http://127.0.0.1:8080/customers";

    public final static String PRODUCT_BASE_URL = "http://127.0.0.1:8080/products";


    @Bean
    public WebClient addressWebClient(ClientHttpConnector clientHttpConnector) {
        return WebClient.builder()
                .baseUrl(ADDRESS_BASE_URL)
                .clientConnector(clientHttpConnector)
                .filter(logRequest())
                .build();
    }

    @Bean
    public WebClient customerWebClient(ClientHttpConnector clientHttpConnector) {
        return WebClient.builder()
                .baseUrl(CUSTOMER_BASE_URL)
                .clientConnector(clientHttpConnector)
                .filter(logRequest())
                .build();
    }

    @Bean
    public WebClient productWebClient(ClientHttpConnector clientHttpConnector) {
        return WebClient.builder()
                .baseUrl(PRODUCT_BASE_URL)
                .clientConnector(clientHttpConnector)
                .filter(logRequest())
                .build();
    }

    @Bean
    public ClientHttpConnector clientHttpConnector() {

/*        return new ReactorClientHttpConnector(options -> {
            options.option(ChannelOption.SO_TIMEOUT, connectionProperties.getSoTimeout());
            options.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionProperties.getConnectTimeout());
            options.poolResources(PoolResources.fixed("myPool", connectionProperties.getMaxPoolSize()));
        });*/

        return new ReactorClientHttpConnector();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}
