package kr.co.owomember.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;

@Getter
@Builder
@AllArgsConstructor
public class OauthRequestDto {
    private String url;
    private LinkedMultiValueMap<String, String> map;
}
