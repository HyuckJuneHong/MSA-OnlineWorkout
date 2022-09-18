package kr.co.owogateway.filters;

import kr.co.owogateway.config.dto.ConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MemberFilter extends AbstractGatewayFilterFactory<ConfigDto> {

    public MemberFilter() {
        super(ConfigDto.class);
    }

    @Override
    public GatewayFilter apply(final ConfigDto configDto) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("MemberFilter baseMessage: {}", configDto.getBaseMessage());

            if(configDto.isPreLogger()){
                log.info("MemberFilter Start: {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(configDto.isPostLogger()){
                    log.info("MemberFilter End: {}", response.getStatusCode());
                }
            }));

        });
    }
}
