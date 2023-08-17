package com.project.npdp.refregirator.entity;

import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.member.entity.Member;
import com.project.npdp.refregirator.dto.request.MemberIngredientModifyRequestDto;
import com.project.npdp.refregirator.dto.request.MemberSeasoningModifyRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;
    @Builder
    public MemberSeasoning(Member member, Seasoning seasoning, Long storage, LocalDateTime startDate) {
        this.member = member;
        this.seasoning = seasoning;
        this.storage = storage;
        this.startDate = startDate;
    }

    public void updateValues(MemberSeasoningModifyRequestDto dto) {
        this.storage = dto.getStorage();
        this.startDate = dto.getStartDate();
        this.expiredDate = dto.getExpiredDate();
    }

}
