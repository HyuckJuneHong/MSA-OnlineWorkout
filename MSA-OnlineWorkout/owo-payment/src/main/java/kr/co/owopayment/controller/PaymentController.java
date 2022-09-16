package kr.co.owopayment.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owopayment.domain.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owo-payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome owo-payment.";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("payment-request") String header){
        log.info(header);
        return "Hello World in owo-payment";
    }
    @GetMapping("/check")
    public String check(){
        return "Hi, Payment Service Check";
    }


    @ApiOperation("상품 등록")
    @PostMapping("/create")
    public void create(@RequestBody PaymentDto.CREATE_PAYMENT payment){
        //TODO: create Payment
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
    public void getPayment(@RequestBody PaymentDto.GET_PAYMENT payment){
        //TODO: get Payment
    }

    @ApiOperation("상품 삭제")
    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody PaymentDto.DELETE_PAYMENT payment){
        //TODO: delete Payment
    }
}