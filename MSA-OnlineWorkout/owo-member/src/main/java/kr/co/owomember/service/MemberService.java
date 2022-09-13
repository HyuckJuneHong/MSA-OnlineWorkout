package kr.co.owomember.service;

import kr.co.owomember.domain.dto.MemberDto;

public interface MemberService {

    //login
    MemberDto.TOKEN login(MemberDto.LOGIN login);
    MemberDto.TOKEN reCreateAccessToken(String refreshToken);

    //create
    void signUp(MemberDto.CREATE_MEMBER member);

    //update
    void update(MemberDto.UPDATE_MEMBER member);
    void updatePassword(MemberDto.UPDATE_PASSWORD password);

    //get
    MemberDto.READ_MEMBER getMember(String identity);

    //delete
    void delete(MemberDto.DELETE_MEMBER member);

    //common
    void checkIdentity(String identity);
    void checkPassword(String password, String checkPassword);
    void checkSignUp(MemberDto.CREATE_MEMBER member);

}
