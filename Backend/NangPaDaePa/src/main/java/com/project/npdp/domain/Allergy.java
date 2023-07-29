package com.project.npdp.domain;

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
    @GeneratedValue
    @Column(name = "allergy_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "allergy")
    private List<MemberAllergy> memberAllergyList = new ArrayList<>();

    @OneToMany(mappedBy = "allergy")
    private List<AllergyIngredient> allergyIngredientList = new ArrayList<>();

}
