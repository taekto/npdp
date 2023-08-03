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

    public final ListPath<com.project.npdp.domain.AllergyIngredient, com.project.npdp.domain.QAllergyIngredient> allergyIngredientList = this.<com.project.npdp.domain.AllergyIngredient, com.project.npdp.domain.QAllergyIngredient>createList("allergyIngredientList", com.project.npdp.domain.AllergyIngredient.class, com.project.npdp.domain.QAllergyIngredient.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final StringPath def = createString("def");

    public final StringPath eng = createString("eng");

    public final StringPath etc = createString("etc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<IngredientSynonym, QIngredientSynonym> ingredientSynonymList = this.<IngredientSynonym, QIngredientSynonym>createList("ingredientSynonymList", IngredientSynonym.class, QIngredientSynonym.class, PathInits.DIRECT2);

    public final StringPath kor = createString("kor");

    public final NumberPath<Long> level = createNumber("level", Long.class);

    public final ListPath<com.project.npdp.member.entity.MemberDislikeIngredient, com.project.npdp.member.entity.QMemberDislikeIngredient> memberDislikeIngredientList = this.<com.project.npdp.member.entity.MemberDislikeIngredient, com.project.npdp.member.entity.QMemberDislikeIngredient>createList("memberDislikeIngredientList", com.project.npdp.member.entity.MemberDislikeIngredient.class, com.project.npdp.member.entity.QMemberDislikeIngredient.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.recipe.entity.RecipeIngredient, com.project.npdp.recipe.entity.QRecipeIngredient> recipeIngredientList = this.<com.project.npdp.recipe.entity.RecipeIngredient, com.project.npdp.recipe.entity.QRecipeIngredient>createList("recipeIngredientList", com.project.npdp.recipe.entity.RecipeIngredient.class, com.project.npdp.recipe.entity.QRecipeIngredient.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.refregirator.entity.Refregirator, com.project.npdp.refregirator.entity.QRefregirator> refregiratorList = this.<com.project.npdp.refregirator.entity.Refregirator, com.project.npdp.refregirator.entity.QRefregirator>createList("refregiratorList", com.project.npdp.refregirator.entity.Refregirator.class, com.project.npdp.refregirator.entity.QRefregirator.class, PathInits.DIRECT2);

    public final StringPath superUpperClass = createString("superUpperClass");

    public final StringPath upperClass = createString("upperClass");

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

