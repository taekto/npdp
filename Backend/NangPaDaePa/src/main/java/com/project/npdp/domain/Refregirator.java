package com.project.npdp.domain;

import com.project.npdp.domain.Ingredient;
import com.project.npdp.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Refregirator {

    @Id
    @GeneratedValue
    @Column(name = "refregirator_id")
    private Long id;

    private Long storage;

    private Double amount;

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

}
