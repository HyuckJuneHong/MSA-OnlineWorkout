package kr.co.owomember.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owomember.client.PaymentServiceClient;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.domain.dto.PaymentDto;
import kr.co.owomember.infra.security.jwt.JwtProvider;
import kr.co.owomember.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PaymentServiceClient paymentServiceClient;
    private final JwtProvider jwtProvider;

    @ApiOperation("login")
    @PostMapping("/login")
    public ResponseFormat<MemberDto.TOKEN> login(@RequestBody @Valid MemberDto.LOGIN login){
        return ResponseFormat.ok(memberService.login(login));
    }

    @ApiOperation("AccessToken 재발급")
    @PostMapping("/refresh")
    public ResponseFormat<String> reCreateAccessToken(@RequestHeader("refreshToken") String refreshToken){
        return ResponseFormat.ok(jwtProvider.reCreateAccessToken(refreshToken));
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberDto.CREATE_MEMBER member){
        memberService.signUp(member);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping
    public ResponseFormat update(@RequestBody @Valid MemberDto.UPDATE_MEMBER member){
        memberService.update(member);
        return ResponseFormat.ok();
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/password")
    public ResponseFormat updatePassword(@RequestBody @Valid MemberDto.UPDATE_PASSWORD password){
        memberService.updatePassword(password);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping
    public ResponseFormat<MemberDto.READ_MEMBER> getMember(){
        return ResponseFormat.ok(memberService.getMember());
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping
    public ResponseFormat deleteMember(@RequestBody @Valid MemberDto.DELETE_MEMBER member){
        memberService.delete(member);
        return ResponseFormat.ok();
    }

    @GetMapping("/orders")
    public ResponseFormat<List<PaymentDto.GET_PAYMENT>> getPayments(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers) {
        return ResponseFormat.ok(paymentServiceClient.getPayments(headers));
    }

    @GetMapping("/payments/welcome")
    public ResponseFormat<String> paymentsWelcome(){
        return ResponseFormat.ok(paymentServiceClient.welcome());
    }

}
