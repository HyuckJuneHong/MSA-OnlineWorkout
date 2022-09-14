package kr.co.owogateway.filters;

import kr.co.owogateway.config.FilterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PaymentFilter extends AbstractGatewayFilterFactory<FilterConfig> {

    public PaymentFilter() {
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(final FilterConfig filterConfig) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("PaymentFilter baseMessage: {}", filterConfig.getBaseMessage());

            if (filterConfig.isPreLogger()) {
                log.info("PaymentFilter Start: {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (filterConfig.isPostLogger()) {
                    log.info("PaymentFilter End:{}", response.getStatusCode());
                }
            }));
        });
    }
}
