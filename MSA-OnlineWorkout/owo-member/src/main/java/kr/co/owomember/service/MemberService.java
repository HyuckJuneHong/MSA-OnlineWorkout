package kr.co.owomember.service;

import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.domain.dto.PaymentDto;
import kr.co.owomember.domain.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    //login
    MemberDto.TOKEN login(MemberDto.LOGIN login);

    //create
    void signUp(MemberDto.CREATE_MEMBER member);

    //update
    void update(MemberDto.UPDATE_MEMBER member);
    void updatePassword(MemberDto.UPDATE_PASSWORD password);

    //get
    MemberDto.READ_MEMBER getMember();
    MemberDto.DETAILS_MEMBER getMemberDetailsByIdentity();
    MemberEntity getThreadLocal();

    //delete
    void delete(MemberDto.DELETE_MEMBER member);

    //common
    void checkIdentity(String identity);
    void checkPassword(String password, String checkPassword);
    void checkEncodePassword(String password, String encodePassword);
    void checkSignUp(MemberDto.CREATE_MEMBER member);
}
