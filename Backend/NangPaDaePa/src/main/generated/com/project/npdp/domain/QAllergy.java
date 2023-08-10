package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAllergy is a Querydsl query type for Allergy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergy extends EntityPathBase<Allergy> {

    private static final long serialVersionUID = 959934228L;

    public static final QAllergy allergy = new QAllergy("allergy");

    public final ListPath<AllergyIngredient, QAllergyIngredient> allergyIngredientList = this.<AllergyIngredient, QAllergyIngredient>createList("allergyIngredientList", AllergyIngredient.class, QAllergyIngredient.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.npdp.member.entity.MemberAllergy, com.project.npdp.member.entity.QMemberAllergy> memberAllergyList = this.<com.project.npdp.member.entity.MemberAllergy, com.project.npdp.member.entity.QMemberAllergy>createList("memberAllergyList", com.project.npdp.member.entity.MemberAllergy.class, com.project.npdp.member.entity.QMemberAllergy.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QAllergy(String variable) {
        super(Allergy.class, forVariable(variable));
    }

    public QAllergy(Path<? extends Allergy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllergy(PathMetadata metadata) {
        super(Allergy.class, metadata);
    }

}

