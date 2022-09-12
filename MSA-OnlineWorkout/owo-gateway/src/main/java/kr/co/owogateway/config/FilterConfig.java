package kr.co.owogateway.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FilterConfig {

    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
