package kr.co.owogateway.filters;

import kr.co.owocommon.error.exception.UnauthorizedException;
import kr.co.owogateway.config.dto.ConfigDto;
import kr.co.owogateway.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthorizationHeadersFilter extends AbstractGatewayFilterFactory<ConfigDto> {

    private final JwtProvider jwtProvider;

    public JwtAuthorizationHeadersFilter(JwtProvider jwtProvider) {
        super(ConfigDto.class);
        this.jwtProvider = jwtProvider;
    }

    /**
     * 사용자 Login 요청 -> Token 반
     * 사용자가 Token 과 함께 서비스 요청 -> Header (Include Token)
     * @param configDto
     * @return
     */
    @Override
    public GatewayFilter apply(ConfigDto configDto) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            jwtProvider.isRequestHeaders(request);
            String token = jwtProvider.resolveToken(request)
                    .orElseThrow(() -> new UnauthorizedException());

            jwtProvider.isUsable(token);

            return chain.filter(exchange);
        });
    }

}
