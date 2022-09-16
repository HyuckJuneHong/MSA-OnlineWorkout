package kr.co.owogateway.filters;

import kr.co.owogateway.config.ConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<ConfigDto> {

    public  CustomFilter(){
        super(ConfigDto.class);
    }

    /**
     * 게이트웨이 필터를 반환시켜줌으로써 어떠한 활동을 하는 메소드
     * 예시: preFilter에 사용자가 인증을 요청했을 때, JWT 를 정상적으로 가지고 있는 체크할 수 있다.
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(ConfigDto config) {

        //Custom Pre Filter
        return (exchange, chain) -> {
            //exchange 로 request, response 객체를 가져올 수 있다. (SererHttp** -> 비동기 방식)
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // preFilter의 ID
            log.info("Custom Pre Filter: request uri -> {}", request.getId());

            //Custom Post Filter
            //처리가 끝난 후 postFilter 상태코드 / Mono : 비동기 방식의 단일 데이터 타입을 주겠다.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Post Filter: response code -> {}", response.getStatusCode());
            }));
        };
    }
}
