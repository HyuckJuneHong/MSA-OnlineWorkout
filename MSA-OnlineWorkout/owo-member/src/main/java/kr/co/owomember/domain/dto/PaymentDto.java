package kr.co.owomember.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PaymentDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GET_PAYMENT{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String paymentCode;

        private String memberIdentity;
    }
}
