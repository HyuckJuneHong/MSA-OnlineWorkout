package kr.co.owopayment.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owopayment.domain.dto.PaymentDto;
import kr.co.owopayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final Environment environment;
    private final PaymentService paymentService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome owo-payment.";
    }

    @GetMapping("/check")
    public String status(){
        return String.format("OWO-PAYMENT Port : %s",
                environment.getProperty("local.server.port"));
    }

    @ApiOperation("상품 등록")
    @PostMapping("/create")
    public ResponseFormat create(@RequestBody PaymentDto.CREATE_PAYMENT payment){
        paymentService.createPayment(payment);
        return ResponseFormat.ok();
    }

    @ApiOperation("상품 중복 체크")
    @PostMapping("/create/check")
    public void checkPayment(){
        //TODO: check payment
    }

    @ApiOperation("상품 정보 수정")
    @PutMapping("/update")
    public void update(@RequestBody PaymentDto.UPDATE_PAYMENT payment){
        //TODO: update
    }

    @ApiOperation("상품 정보 조회")
    @GetMapping()
    public ResponseFormat<PaymentDto.GET_PAYMENT> getPayment(@RequestParam("paymentCode") String paymentCode){
        return ResponseFormat.ok(paymentService.getPaymentByPaymentCode(paymentCode));
    }

    @ApiOperation("모든 상품 정보 조회")
    @GetMapping("/all")
    public ResponseFormat<List<PaymentDto.GET_PAYMENT>> getPayments(@RequestParam("memberIdentity") String memberIdentity){
        return ResponseFormat.ok(paymentService.getPaymentsByMemberIdentity(memberIdentity));
    }

    @ApiOperation("상품 삭제")
    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody PaymentDto.DELETE_PAYMENT payment){
        //TODO: delete Payment
    }
}