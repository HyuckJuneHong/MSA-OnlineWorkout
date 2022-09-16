package kr.co.owogateway.filters;

import kr.co.owogateway.config.ConfigDto;
import kr.co.owogateway.jwt.JwtProvider;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthorizationHeadersFilter extends AbstractGatewayFilterFactory<ConfigDto> {

    private final JwtProvider jwtProvider;

    public JwtAuthorizationHeadersFilter(JwtProvider jwtProvider) {
        super(ConfigDto.class);
        this.jwtProvider = jwtProvider;
    }

    @Override
    public GatewayFilter apply(ConfigDto configDto) {
        return ((exchange, chain) -> {

            final Optional<String> token = jwtProvider.resolveToken(exchange.getRequest());
            if(token.isPresent()){
                return jwtProvider.onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            }
            if(jwtProvider.isUsable(token.get())){
                Authentication authentication = jwtProvider.getAuthentication(token.get());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            return chain.filter(exchange);
        });
    }

}
