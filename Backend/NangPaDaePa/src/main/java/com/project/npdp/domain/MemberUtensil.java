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
public class MemberUtensil {

    @Id @GeneratedValue
    @Column(name = "member_utensil_id")
    private Long id;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // utensil(장비) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utensil_id")
    private Utensil utensil;

}
