package com.ssafy.npdp.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberSeasoning {

    @Id
    @GeneratedValue
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

    private int storage;

}
