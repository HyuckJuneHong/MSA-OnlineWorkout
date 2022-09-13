package kr.co.owomember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation("login")
    @PostMapping("/login")
    public ResponseFormat<MemberDto.TOKEN> login(@RequestBody MemberDto.LOGIN login){
        //TODO: login
        return null;
    }

    @ApiOperation("AccessToken 재발급")
    @DeleteMapping("/refresh")
    public ResponseFormat<MemberDto.TOKEN> reCreateAccessToken(){
        //TODO: Access Token Reset
        return null;
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody MemberDto.CREATE_MEMBER member){
        memberService.signUp(member);
        return ResponseFormat.ok();
    }

    @ApiOperation("아이디 중복 체크")
    @PostMapping("/signUp/check")
    public ResponseFormat<Boolean> checkIdentity(){
        //TODO: check identity
        return null;
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/update")
    public ResponseFormat update(@RequestBody MemberDto.UPDATE_MEMBER member){
        //TODO: update
        return ResponseFormat.ok();
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/update/password")
    public ResponseFormat updatePassword(@RequestBody MemberDto.UPDATE_PASSWORD password){
        //TODO: update Password
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping()
    public ResponseFormat<MemberDto.READ_MEMBER> getMember(){
        //TODO: get Member
        return null;
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping("/delete")
    public ResponseFormat deleteMember(@RequestBody MemberDto.DELETE_MEMBER member){
        //TODO: delete Member
        return ResponseFormat.ok();
    }
}
