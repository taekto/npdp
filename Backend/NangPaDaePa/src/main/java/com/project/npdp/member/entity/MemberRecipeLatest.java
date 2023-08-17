package com.project.npdp.member.entity;

import com.project.npdp.recipe.entity.Recipe;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberRecipeLatest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_recipe_latest_id")
    private Long id;

    private LocalDateTime date;

    // recipe(레시피) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberRecipeLatest(Recipe recipe, Member member) {
        this.recipe = recipe;
        this.member = member;
        this.date = LocalDateTime.now();
    }

    // 날짜 업데이트
    public void updateDate() {
        this.date = LocalDateTime.now();
    }


}
