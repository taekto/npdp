package com.project.npdp.member.entity;

import com.project.npdp.domain.Allergy;
import com.project.npdp.food.entity.Ingredient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_allergy_id")
    private Long id;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // allergy(알러지) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allergy_id")
    private Allergy allergy;

    // 회원이 갖는 알러지
    public MemberAllergy(Member member, Allergy allergy){
        this.member = member;
        this.allergy = allergy;
    }
}
