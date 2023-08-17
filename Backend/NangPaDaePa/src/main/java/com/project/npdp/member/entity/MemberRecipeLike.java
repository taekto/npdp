package com.project.npdp.member.entity;

import com.project.npdp.recipe.entity.Recipe;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberRecipeLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_recipe_like_id")
    private Long id;

    // recipe(레시피) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberRecipeLike(Recipe recipe, Member member) {
        this.recipe = recipe;
        this.member = member;
    }

}
