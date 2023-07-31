package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.IngredientSynonym;
import com.project.npdp.member.entity.MemberDislikeIngredient;
import com.project.npdp.recipe.entity.RecipeIngredient;
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

    private static final long serialVersionUID = -1230516803L;

    public static final QIngredient ingredient = new QIngredient("ingredient");

    public final ListPath<AllergyIngredient, QAllergyIngredient> allergyIngredientList = this.<AllergyIngredient, QAllergyIngredient>createList("allergyIngredientList", AllergyIngredient.class, QAllergyIngredient.class, PathInits.DIRECT2);

    public final StringPath def = createString("def");

    public final StringPath eng = createString("eng");

    public final StringPath etc = createString("etc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<IngredientSynonym, QIngredientSynonym> ingredientSynonymList = this.<IngredientSynonym, QIngredientSynonym>createList("ingredientSynonymList", IngredientSynonym.class, QIngredientSynonym.class, PathInits.DIRECT2);

    public final StringPath kor = createString("kor");

    public final NumberPath<Long> level = createNumber("level", Long.class);

    public final ListPath<MemberDislikeIngredient, QMemberDislikeIngredient> memberDislikeIngredientList = this.<MemberDislikeIngredient, QMemberDislikeIngredient>createList("memberDislikeIngredientList", MemberDislikeIngredient.class, QMemberDislikeIngredient.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<RecipeIngredient, QRecipeIngredient> recipeIngredientList = this.<RecipeIngredient, QRecipeIngredient>createList("recipeIngredientList", RecipeIngredient.class, QRecipeIngredient.class, PathInits.DIRECT2);

    public final ListPath<Refregirator, QRefregirator> refregiratorList = this.<Refregirator, QRefregirator>createList("refregiratorList", Refregirator.class, QRefregirator.class, PathInits.DIRECT2);

    public final StringPath supperUpperClass = createString("supperUpperClass");

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
