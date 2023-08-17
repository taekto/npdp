package com.project.npdp.member.entity;

import com.project.npdp.food.entity.Ingredient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Getter
public class MemberDislikeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_dislike_ingredient_id")
    private Long id;

    // ingredient(재료) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    // 비선호하는 재료
    public MemberDislikeIngredient(Member member, Ingredient ingredient){
        this.member = member;
        this.ingredient = ingredient;
    }
}
