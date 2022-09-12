package kr.co.owopayment.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    @ApiOperation("상품 등록")
    @PostMapping("/create")
    public void create(){
        //TODO: create Payment
    }

    @ApiOperation("상품 중복 체크")
    @PostMapping("/create/check")
    public void checkPayment(){
        //TODO: check payment
    }

    @ApiOperation("상품 정보 수정")
    @PutMapping("/update")
    public void update(){
        //TODO: update
    }

    @ApiOperation("상품 정보 조회")
    @GetMapping()
    public void getPayment(){
        //TODO: get Payment
    }

    @ApiOperation("상품 삭제")
    @DeleteMapping("/delete")
    public void deleteMember(){
        //TODO: delete Payment
    }
}
