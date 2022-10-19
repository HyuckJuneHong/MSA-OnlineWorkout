package kr.co.owopayment.domain.entity;

import kr.co.owopayment.domain.dto.PaymentDto;
import kr.co.owopayment.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "payment_id"))
@Getter
public class PaymentEntity extends BaseEntity {

    @Column(name = "product_code", nullable = false, length = 120, unique = true)
    private String productCode;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;

    @Column(name = "price")
    private int totalPrice;

    @Column(name = "member_identity", nullable = false, length = 120, unique = true)
    private String memberIdentity;

    @Column(name = "payment_code", nullable = false, length = 120, unique = true)
    private String paymentCode;

    @Builder
    public PaymentEntity(String productCode,
                         int amount,
                         int price,
                         int totalPrice,
                         String memberIdentity,
                         String paymentCode) {
        this.productCode = productCode;
        this.amount = amount;
        this.price = price;
        this.totalPrice = totalPrice;
        this.memberIdentity = memberIdentity;
        this.paymentCode = paymentCode;
    }

    public static PaymentEntity of(PaymentDto.CREATE_PAYMENT payment){
        return PaymentEntity.builder()
                .paymentCode(payment.getPaymentCode())
                .amount(payment.getAmount())
                .price(payment.getPrice())
                .totalPrice(payment.getTotalPrice())
                .productCode(payment.getProductCode())
                .memberIdentity(payment.getMemberIdentity())
                .build();
    }

}
