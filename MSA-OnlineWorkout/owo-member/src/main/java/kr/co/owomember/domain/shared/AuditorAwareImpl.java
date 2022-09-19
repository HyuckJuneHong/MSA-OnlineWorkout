package kr.co.owomember.domain.shared;

import kr.co.owocommon.error.exception.BadRequestException;
import kr.co.owomember.domain.dto.AuthenticartionMemberDto;
import kr.co.owomember.domain.entity.MemberEntity;
import kr.co.owomember.domain.shared.enums.MemberRole;
import kr.co.owomember.infra.interceptor.MemberThreadLocal;
import kr.co.owomember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final MemberRepository memberRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        String identity = MemberThreadLocal.get();
        MemberEntity memberEntity = memberRepository.findByIdentity(identity)
                .orElseThrow(() -> new BadRequestException("id가 잘못된 요청"));

        AuthenticartionMemberDto authenticartionMemberDto = AuthenticartionMemberDto.builder()
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .build();

        return Optional.of(authenticartionMemberDto.getIdentity());
    }
}
