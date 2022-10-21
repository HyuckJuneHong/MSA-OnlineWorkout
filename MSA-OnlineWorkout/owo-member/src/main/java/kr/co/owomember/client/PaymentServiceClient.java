package kr.co.owomember.client;

import kr.co.owomember.domain.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


@FeignClient(name = "owo-payment")
public interface PaymentServiceClient {

    @GetMapping("/payments/all")
    List<PaymentDto.GET_PAYMENT> getPayments(@RequestHeader(HttpHeaders.AUTHORIZATION) String header);

    @GetMapping("/payments/welcome")
    String welcome();
}
