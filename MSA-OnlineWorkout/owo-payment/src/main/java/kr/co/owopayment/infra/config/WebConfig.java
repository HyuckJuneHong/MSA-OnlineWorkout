package kr.co.owopayment.infra.config;

import kr.co.owocommon.jwt.JwtProviderCommon;
import kr.co.owopayment.infra.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECOND = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECOND);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("members/login");
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor(jwtProviderCommon());
    }

    @Bean
    public JwtProviderCommon jwtProviderCommon(){
        return new JwtProviderCommon();
    }

}