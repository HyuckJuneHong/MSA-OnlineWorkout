package kr.co.owomember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/owo-member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome owo-member.";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("member-request") String header){
        log.info(header);
        return "Hello World in owo-member";
    }
    @GetMapping("/check")
    public String check(){
        return "Hi, Member Service Check";
    }

    @ApiOperation("login")
    @PostMapping("/login")
    public ResponseFormat<MemberDto.TOKEN> login(@RequestBody @Valid MemberDto.LOGIN login){
        return ResponseFormat.ok(memberService.login(login));
    }

    @ApiOperation("AccessToken 재발급")
    @DeleteMapping("/refresh")
    public ResponseFormat<MemberDto.TOKEN> reCreateAccessToken(@RequestHeader("token") String refreshToken){
        return ResponseFormat.ok(memberService.reCreateAccessToken(refreshToken));
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberDto.CREATE_MEMBER member){
        memberService.signUp(member);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/update")
    public ResponseFormat update(@RequestBody @Valid MemberDto.UPDATE_MEMBER member){
        memberService.update(member);
        return ResponseFormat.ok();
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/update/password")
    public ResponseFormat updatePassword(@RequestBody @Valid MemberDto.UPDATE_PASSWORD password){
        memberService.updatePassword(password);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping()
    public ResponseFormat<MemberDto.READ_MEMBER> getMember(@RequestParam("identity") String identity){
        return ResponseFormat.ok(memberService.getMember(identity));
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping("/delete")
    public ResponseFormat deleteMember(@RequestBody @Valid MemberDto.DELETE_MEMBER member){
        memberService.delete(member);
        return ResponseFormat.ok();
    }
}
