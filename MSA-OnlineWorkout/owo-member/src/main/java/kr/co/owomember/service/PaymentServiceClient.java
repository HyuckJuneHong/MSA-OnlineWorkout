package kr.co.owomember.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "owo-payment")
public interface PaymentServiceClient {
}
