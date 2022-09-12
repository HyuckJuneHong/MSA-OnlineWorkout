package kr.co.owomember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owomember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation("login")
    @PostMapping("/login")
    public void login(){
        //TODO: login
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public void signUp(){
        //TODO: signUP
    }

    @ApiOperation("회원가입")
    @PostMapping("/signUp/check")
    public void checkIdentity(){
        //TODO: check identity
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/update")
    public void update(){
        //TODO: update
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/update/password")
    public void updatePassword(){
        //TODO: update Password
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping()
    public void getMember(){
        //TODO: get Member
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping("/delete")
    public void deleteMember(){
        //TODO: delete Member
    }

    @ApiOperation("AccessToken 재발급")
    @DeleteMapping("/refresh")
    public void resetAccessToken(){
        //TODO: Access Token Reset
    }
}
