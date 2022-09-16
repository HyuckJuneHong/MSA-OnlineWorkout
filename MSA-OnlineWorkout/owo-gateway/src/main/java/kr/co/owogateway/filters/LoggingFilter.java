package kr.co.owogateway.filters;

import kr.co.owogateway.config.ConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
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
public class LoggingFilter extends AbstractGatewayFilterFactory<ConfigDto> {

    public LoggingFilter(){
        super(ConfigDto.class);
    }

    @Override
    public GatewayFilter apply(final ConfigDto configDto) {
        GatewayFilter gatewayFilter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //가장 먼저 실행.
            log.info("Logging Filter baseMessage: {}", configDto.getBaseMessage());
            if(configDto.isPreLogger()) {
                log.info("Logging Pre Filter: {}", request.getId());
            }

            //가장 마지막에 실행
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(configDto.isPostLogger()) {
                    log.info("Logging Post Filter: {}", response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE); //2번째 파라미터 : Filter 의 우선 순위값

        return gatewayFilter;
    }
}
