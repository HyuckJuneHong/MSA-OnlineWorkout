package kr.co.owopayment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PaymentDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CREATE_PAYMENT{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String paymentCode;

        private String memberIdentity;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UPDATE_PAYMENT{
        //TODO payment update
    }

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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DELETE_PAYMENT{
        //TODO payment delete
    }

}
