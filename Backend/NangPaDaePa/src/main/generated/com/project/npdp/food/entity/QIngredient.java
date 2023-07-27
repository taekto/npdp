package com.project.npdp.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredient is a Querydsl query type for Ingredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredient extends EntityPathBase<Ingredient> {

    private static final long serialVersionUID = -1462455254L;

    public static final QIngredient ingredient = new QIngredient("ingredient");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.npdp.member.entity.MemberAllergy, com.project.npdp.member.entity.QMemberAllergy> memberAllergyList = this.<com.project.npdp.member.entity.MemberAllergy, com.project.npdp.member.entity.QMemberAllergy>createList("memberAllergyList", com.project.npdp.member.entity.MemberAllergy.class, com.project.npdp.member.entity.QMemberAllergy.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.member.entity.MemberDislikeIngredient, com.project.npdp.member.entity.QMemberDislikeIngredient> memberDislikeIngredientList = this.<com.project.npdp.member.entity.MemberDislikeIngredient, com.project.npdp.member.entity.QMemberDislikeIngredient>createList("memberDislikeIngredientList", com.project.npdp.member.entity.MemberDislikeIngredient.class, com.project.npdp.member.entity.QMemberDislikeIngredient.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<com.project.npdp.recipe.entity.RecipeIngredient, com.project.npdp.recipe.entity.QRecipeIngredient> recipeIngredientList = this.<com.project.npdp.recipe.entity.RecipeIngredient, com.project.npdp.recipe.entity.QRecipeIngredient>createList("recipeIngredientList", com.project.npdp.recipe.entity.RecipeIngredient.class, com.project.npdp.recipe.entity.QRecipeIngredient.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.refrigerator.entity.Refregirator, com.project.npdp.refrigerator.entity.QRefregirator> refregiratorList = this.<com.project.npdp.refrigerator.entity.Refregirator, com.project.npdp.refrigerator.entity.QRefregirator>createList("refregiratorList", com.project.npdp.refrigerator.entity.Refregirator.class, com.project.npdp.refrigerator.entity.QRefregirator.class, PathInits.DIRECT2);

    public QIngredient(String variable) {
        super(Ingredient.class, forVariable(variable));
    }

    public QIngredient(Path<? extends Ingredient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredient(PathMetadata metadata) {
        super(Ingredient.class, metadata);
    }

}

