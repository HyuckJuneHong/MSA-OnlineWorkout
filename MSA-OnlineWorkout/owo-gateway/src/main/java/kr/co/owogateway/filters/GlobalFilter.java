package kr.co.owogateway.filters;

import kr.co.owogateway.config.FilterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * AbstraceGatewayFilterFactory : Gateway를 구현하기 위한 추상 클래스
 * exchange : 서비스 요청/응답값을 담고있는 변수로, 요청/응답값을 출력하거나 변환할 때 사용한다.
 * 요청값은 (exchange, chain) -> 구문 이후에 얻을 수 있으며, 서비스로부터 리턴받은 응답값은 Mono.fromRunnable(()-> 구문 이후부터 얻을 수 있다.
 */
@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<FilterConfig> {

    public GlobalFilter(){
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(FilterConfig filterConfig) {
        return ((exchange,chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("GlobalFilter baseMessage: {}", filterConfig.getBaseMessage());

            if(filterConfig.isPreLogger()) {
                log.info("GlobalFilter Start: {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(filterConfig.isPostLogger()) {
                    log.info("GlobalFilter End:{}", response.getStatusCode());
                }
            }));
        });
    }
}
