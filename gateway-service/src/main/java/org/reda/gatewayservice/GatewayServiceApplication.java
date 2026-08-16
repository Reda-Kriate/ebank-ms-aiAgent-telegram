package org.reda.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean //routage dynamique
    public DiscoveryClientRouteDefinitionLocator routes(
            ReactiveDiscoveryClient dlc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(dlc,dlp);
    }

}
