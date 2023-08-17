package com.project.npdp.refregirator.entity;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.member.entity.Member;
import com.project.npdp.refregirator.dto.request.MemberIngredientModifyRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Refregirator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refregirator_id")
    private Long id;

    private Long storage;

    private String amount;

    private String unit;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // ingredient(재료) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Builder
    public Refregirator(Long storage, String amount, String unit, LocalDateTime startDate, LocalDateTime expiredDate, Member member, Ingredient ingredient) {
        this.storage = storage;
        this.amount = amount;
        this.unit = unit;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.member = member;
        this.ingredient = ingredient;
    }

    public void updateValues(MemberIngredientModifyRequestDto dto) {
        this.storage = dto.getStorage();
        this.amount = dto.getAmount();
        this.unit = dto.getUnit();
        this.startDate = dto.getStartDate();
        this.expiredDate = dto.getExpiredDate();
    }

}
