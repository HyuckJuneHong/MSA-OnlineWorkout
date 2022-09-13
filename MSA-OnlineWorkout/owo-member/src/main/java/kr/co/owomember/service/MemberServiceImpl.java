package kr.co.owomember.service;

import kr.co.owomember.domain.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Override
    public MemberDto.TOKEN login(MemberDto.LOGIN login) {
        return null;
    }

    @Override
    public MemberDto.TOKEN reCreateAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public void signUp(MemberDto.CREATE_MEMBER member) {

    }

    @Override
    public void update(MemberDto.UPDATE_MEMBER member) {

    }

    @Override
    public void updatePassword(MemberDto.UPDATE_PASSWORD password) {

    }

    @Override
    public MemberDto.READ_MEMBER getMember() {
        return null;
    }

    @Override
    public boolean checkIdentity(String identity) {
        return false;
    }
}
