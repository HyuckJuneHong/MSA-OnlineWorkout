package kr.co.owocatalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CatalogDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CATALOG{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String paymentCode;

        private String memberIdentity;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String paymentCode;

        private String memberIdentity;
    }
}
