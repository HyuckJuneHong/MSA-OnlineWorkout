package kr.co.owogateway.config.dto;

import lombok.Data;


//Configration 정보
@Data
public class ConfigDto {

    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
