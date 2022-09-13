package kr.co.owomember.service;

import kr.co.owocommon.error.exception.DuplicatedException;
import kr.co.owocommon.error.model.ErrorCode;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.domain.entity.MemberEntity;
import kr.co.owomember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberDto.TOKEN login(MemberDto.LOGIN login) {
        return null;
    }

    @Override
    public MemberDto.TOKEN reCreateAccessToken(String refreshToken) {
        return null;
    }

    /**
     * 회원 가입 서비스
     * @param member 회원 가입에 필요한 정보
     */
    @Override
    public void signUp(MemberDto.CREATE_MEMBER member) {
        checkSignUp(member);
        memberRepository.save(MemberEntity.of(member));
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

    /**
     * 아이디 중복 체크
     * @param identity 검사할 아이디
     */
    @Override
    public void checkIdentity(String identity) {
        if(memberRepository.existsByIdentity(identity)){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }
    }

    /**
     * 회원가입에 필요한 모든 체크
     * @param member 회원가입에 필요한 정보
     */
    @Override
    public void checkSignUp(MemberDto.CREATE_MEMBER member) {
        checkIdentity(member.getIdentity());
    }
}
