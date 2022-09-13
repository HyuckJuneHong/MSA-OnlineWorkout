package kr.co.owomember.repository;

import kr.co.owomember.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //find
    Optional<MemberEntity> findByIdentity(String identity);

    //check
    boolean existsByIdentity(String identity);
}
