package com.project.npdp.refregirator.entity;

import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberSeasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seasoning_id")
    private Long id;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // seasoning(양념) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seasoning_id")
    private Seasoning seasoning;

    private Long storage;
    @Builder
    public MemberSeasoning(Member member, Seasoning seasoning, Long storage) {
        this.member = member;
        this.seasoning = seasoning;
        this.storage = storage;
    }

}
