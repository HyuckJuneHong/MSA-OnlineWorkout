package kr.co.owomember.repository;

import kr.co.owomember.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //check
    boolean existsByIdentity(String identity);
}
