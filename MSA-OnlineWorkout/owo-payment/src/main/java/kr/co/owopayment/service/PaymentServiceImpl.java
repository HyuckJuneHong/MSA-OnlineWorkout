package kr.co.owopayment.service;

import kr.co.owocommon.error.exception.BadRequestException;
import kr.co.owopayment.domain.dto.PaymentDto;
import kr.co.owopayment.domain.entity.PaymentEntity;
import kr.co.owopayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public void createPayment(PaymentDto.CREATE_PAYMENT createPayment) {
        paymentRepository.save(PaymentEntity.of(createPayment));
    }

    @Override
    public PaymentDto.GET_PAYMENT getPaymentByPaymentCode(String paymentCode) {
        PaymentEntity paymentEntity = paymentRepository.findByPaymentCode(paymentCode)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 상품입니다."));

        return PaymentDto.GET_PAYMENT.builder()
                .paymentCode(paymentEntity.getPaymentCode())
                .paymentCode(paymentEntity.getPaymentCode())
                .amount(paymentEntity.getAmount())
                .price(paymentEntity.getPrice())
                .totalPrice(paymentEntity.getTotalPrice())
                .productCode(paymentEntity.getProductCode())
                .memberIdentity(paymentEntity.getMemberIdentity())
                .build();
    }

    @Override
    public List<PaymentDto.GET_PAYMENT> getPaymentsByMemberIdentity(String identity) {
        List<PaymentEntity> list = paymentRepository.findPaymentsByMemberIdentity(identity);

        List<PaymentDto.GET_PAYMENT> payments = new ArrayList<>();
        for(PaymentEntity paymentEntity : list){
            PaymentDto.GET_PAYMENT payment = PaymentDto.GET_PAYMENT.builder()
                    .paymentCode(paymentEntity.getPaymentCode())
                    .paymentCode(paymentEntity.getPaymentCode())
                    .amount(paymentEntity.getAmount())
                    .price(paymentEntity.getPrice())
                    .totalPrice(paymentEntity.getTotalPrice())
                    .productCode(paymentEntity.getProductCode())
                    .memberIdentity(paymentEntity.getMemberIdentity())
                    .build();
            payments.add(payment);
        }
        return payments;
    }
}
