package kr.co.owopayment.service;

import kr.co.owopayment.domain.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    //create
    void createPayment(PaymentDto.CREATE_PAYMENT createPayment);

    //get
    PaymentDto.GET_PAYMENT getPaymentByPaymentCode(String paymentCode);
    List<PaymentDto.GET_PAYMENT> getPaymentsByMemberIdentity();
}
