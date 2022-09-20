package kr.co.owomember.service;

import kr.co.owocommon.error.exception.BadRequestException;
import kr.co.owocommon.error.exception.BusinessLogicException;
import kr.co.owocommon.error.exception.DuplicatedException;
import kr.co.owocommon.error.model.ErrorCode;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.domain.entity.MemberEntity;
import kr.co.owomember.infra.interceptor.MemberThreadLocal;
import kr.co.owomember.infra.security.jwt.JwtProvider;
import kr.co.owomember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    /**
     * 로그인
     * @param login
     * @return
     */
    @Override
    public MemberDto.TOKEN login(MemberDto.LOGIN login) {
        MemberEntity memberEntity = memberRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 회원입니다."));
        checkEncodePassword(login.getPassword(), memberEntity.getPassword());

        String[] tokens = generateToken(memberEntity);
        redisService.createData(memberEntity.getIdentity(), tokens[1], jwtProvider.getREFRESH_EXPIRE());

        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

    @Override
    public MemberDto.TOKEN reCreateAccessToken(String refreshToken) {
        MemberEntity memberEntity = getThreadLocal();
        redisService.checkValue(refreshToken, redisService.getValue(memberEntity.getIdentity()));
        String[] tokens = generateToken(memberEntity);

        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

    /**
     * 회원 가입 서비스
     * @param member 회원 가입에 필요한 정보
     */
    @Override
    public void signUp(MemberDto.CREATE_MEMBER member) {
        checkSignUp(member);
        memberRepository.save(MemberEntity.of(member, passwordEncoder));
    }

    /**
     * 회원 정보 변경
     * @param member 변경할 회원의 정보
     */
    @Override
    public void update(MemberDto.UPDATE_MEMBER member) {
        MemberEntity memberEntity = getThreadLocal();
        memberEntity.updateName(member.getName());
        memberRepository.save(memberEntity);
    }

    /**
     * 비밀번호 변경
     * @param password : 변경할 비밀번호 정보
     */
    @Override
    public void updatePassword(MemberDto.UPDATE_PASSWORD password) {
        MemberEntity memberEntity = getThreadLocal();
        checkEncodePassword(password.getOldPassword(), memberEntity.getPassword());
        checkPassword(password.getNewPassword(), password.getCheckPassword());
        memberEntity.updatePassword(passwordEncoder.encode(password.getNewPassword()));
        memberRepository.save(memberEntity);
    }

    /**
     * 회원 정보 조회
     * @return
     */
    @Override
    public MemberDto.READ_MEMBER getMember() {
        MemberEntity memberEntity = getThreadLocal();

        return MemberDto.READ_MEMBER.builder()
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .build();
    }

    /**
     * Security Authentiocation Filter Test Get Method
     * @return
     */
    @Override
    public MemberDto.DETAILS_MEMBER getMemberDetailsByIdentity() {
        MemberEntity memberEntity = getThreadLocal();

        return MemberDto.DETAILS_MEMBER.builder()
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .memberRole(String.valueOf(memberEntity.getMemberRole()))
                .build();
    }

    @Override
    public MemberEntity getThreadLocal() {
        String identity = MemberThreadLocal.get();
        return memberRepository.findByIdentity(identity)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원 탈퇴
     * @param member 탈퇴할 정보
     */
    @Override
    public void delete(MemberDto.DELETE_MEMBER member) {
        MemberEntity memberEntity = getThreadLocal();
        checkEncodePassword(member.getPassword(), memberEntity.getPassword());

        memberRepository.delete(memberEntity);
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
     * 비밀번호 체크
     * @param password 비밀번호
     * @param checkPassword 확인할 비밀번호
     */
    @Override
    public void checkPassword(String password, String checkPassword) {
        if(!password.equals(checkPassword)){
            throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
        }
    }

    /**
     * 암호화된 비밀번호 체크
     * @param password 확인할 비밀번호
     * @param encodePassword 암호화된비밀번호
     */
    @Override
    public void checkEncodePassword(String password, String encodePassword) {
        if(!passwordEncoder.matches(password, encodePassword)){
            throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
        }
    }

    /**
     * 회원가입에 필요한 모든 체크
     * @param member 회원가입에 필요한 정보
     */
    @Override
    public void checkSignUp(MemberDto.CREATE_MEMBER member) {
        checkIdentity(member.getIdentity());
        checkPassword(member.getPassword(), member.getCheckPassword());
    }

    /**
     * 토큰 생성
     * @param member : 토큰 생성할 회원
     * @return
     */
    private String[] generateToken(MemberEntity member){
        String accessToken = jwtProvider
                .createAccessToken(member.getIdentity(), member.getMemberRole(), member.getName());
        String refreshToken = jwtProvider
                .createRefreshToken(member.getIdentity(), member.getMemberRole(), member.getName());
        return new String[]{accessToken, refreshToken};
    }
}
