package kr.co.owomember.controller;

import io.swagger.annotations.ApiOperation;
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
    public void login(@RequestBody MemberDto.LOGIN login){
        //TODO: login
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public void signUp(@RequestBody MemberDto.CREATE_MEMBER member){
        //TODO: signUP
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp/check")
    public void checkIdentity(){
        //TODO: check identity
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/update")
    public void update(@RequestBody MemberDto.UPDATE_MEMBER member){
        //TODO: update
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/update/password")
    public void updatePassword(@RequestBody MemberDto.UPDATE_PASSWORD password){
        //TODO: update Password
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping()
    public void getMember(@RequestBody MemberDto.GET_MEMBER member){
        //TODO: get Member
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody MemberDto.DELETE_MEMBER member){
        //TODO: delete Member
    }

    @ApiOperation("AccessToken 재발급")
    @DeleteMapping("/refresh")
    public void resetAccessToken(){
        //TODO: Access Token Reset
    }
}
