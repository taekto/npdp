package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAllergyIngredient is a Querydsl query type for AllergyIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergyIngredient extends EntityPathBase<AllergyIngredient> {

    private static final long serialVersionUID = -1772424187L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAllergyIngredient allergyIngredient = new QAllergyIngredient("allergyIngredient");

    public final QAllergy allergy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.npdp.food.entity.QIngredient ingredient;

    public QAllergyIngredient(String variable) {
        this(AllergyIngredient.class, forVariable(variable), INITS);
    }

    public QAllergyIngredient(Path<? extends AllergyIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAllergyIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAllergyIngredient(PathMetadata metadata, PathInits inits) {
        this(AllergyIngredient.class, metadata, inits);
    }

    public QAllergyIngredient(Class<? extends AllergyIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.allergy = inits.isInitialized("allergy") ? new QAllergy(forProperty("allergy")) : null;
        this.ingredient = inits.isInitialized("ingredient") ? new com.project.npdp.food.entity.QIngredient(forProperty("ingredient")) : null;
    }

}

