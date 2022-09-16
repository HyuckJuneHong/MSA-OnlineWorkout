package kr.co.owogateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * Java Code 를 활용해 gateway 에 등록하는 방법.
 */
//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/owo-member/**")
                             .filters(f->f.addRequestHeader("member-request", "member-request-header")
                                          .addResponseHeader("member-response", "member-response-header"))
                        .uri("http://localhost:8001"))
                .route(r -> r.path("/owo-payment/**")
                            .filters(f->f.addRequestHeader("payment-request", "payment-request-header")
                                         .addResponseHeader("payment-response", "payment-response-header"))
                            .uri("http://localhost:8002"))
                .build();
    }
}
