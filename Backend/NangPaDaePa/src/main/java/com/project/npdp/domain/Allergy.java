package com.project.npdp.domain;

import com.project.npdp.domain.AllergyIngredient;
import com.project.npdp.member.entity.MemberAllergy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "allergy")
    private List<MemberAllergy> memberAllergyList = new ArrayList<>();

    @OneToMany(mappedBy = "allergy")
    private List<AllergyIngredient> allergyIngredientList = new ArrayList<>();

}
