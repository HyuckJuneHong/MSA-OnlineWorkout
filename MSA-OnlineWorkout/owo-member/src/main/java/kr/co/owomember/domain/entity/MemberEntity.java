package kr.co.owomember.domain.entity;

import kr.co.owomember.domain.shared.BaseEntity;
import kr.co.owomember.domain.shared.enums.MemberRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@Getter
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Column(name = "identity", unique = true, nullable = false, length = 100)
    private String identity;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "member_role")
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    public MemberEntity(String identity,
                        String password,
                        String name,
                        MemberRole memberRole) {
        this.identity = identity;
        this.password = password;
        this.name = name;
        this.memberRole = memberRole;
    }
}