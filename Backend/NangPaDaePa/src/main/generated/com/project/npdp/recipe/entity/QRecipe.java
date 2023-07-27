package com.project.npdp.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = 1080730135L;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final ListPath<com.project.npdp.member.entity.Calendar, com.project.npdp.member.entity.QCalendar> calendarList = this.<com.project.npdp.member.entity.Calendar, com.project.npdp.member.entity.QCalendar>createList("calendarList", com.project.npdp.member.entity.Calendar.class, com.project.npdp.member.entity.QCalendar.class, PathInits.DIRECT2);

    public final NumberPath<Double> calorie = createNumber("calorie", Double.class);

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final StringPath category = createString("category");

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgBig = createString("imgBig");

    public final StringPath imgSmall = createString("imgSmall");

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final ListPath<com.project.npdp.member.entity.MemberRecipeLatest, com.project.npdp.member.entity.QMemberRecipeLatest> memberRecipeLatestList = this.<com.project.npdp.member.entity.MemberRecipeLatest, com.project.npdp.member.entity.QMemberRecipeLatest>createList("memberRecipeLatestList", com.project.npdp.member.entity.MemberRecipeLatest.class, com.project.npdp.member.entity.QMemberRecipeLatest.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.member.entity.MemberRecipeLike, com.project.npdp.member.entity.QMemberRecipeLike> memberRecipeLikeList = this.<com.project.npdp.member.entity.MemberRecipeLike, com.project.npdp.member.entity.QMemberRecipeLike>createList("memberRecipeLikeList", com.project.npdp.member.entity.MemberRecipeLike.class, com.project.npdp.member.entity.QMemberRecipeLike.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final ListPath<RecipeIngredient, QRecipeIngredient> recipeIngredientList = this.<RecipeIngredient, QRecipeIngredient>createList("recipeIngredientList", RecipeIngredient.class, QRecipeIngredient.class, PathInits.DIRECT2);

    public final ListPath<RecipeSeasoning, QRecipeSeasoning> recipeSeasoningList = this.<RecipeSeasoning, QRecipeSeasoning>createList("recipeSeasoningList", RecipeSeasoning.class, QRecipeSeasoning.class, PathInits.DIRECT2);

    public final ListPath<RecipeSequence, QRecipeSequence> recipeSequenceList = this.<RecipeSequence, QRecipeSequence>createList("recipeSequenceList", RecipeSequence.class, QRecipeSequence.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.utensil.entity.RecipeUtensil, com.project.npdp.utensil.entity.QRecipeUtensil> recipeUtensilList = this.<com.project.npdp.utensil.entity.RecipeUtensil, com.project.npdp.utensil.entity.QRecipeUtensil>createList("recipeUtensilList", com.project.npdp.utensil.entity.RecipeUtensil.class, com.project.npdp.utensil.entity.QRecipeUtensil.class, PathInits.DIRECT2);

    public final NumberPath<Double> salt = createNumber("salt", Double.class);

    public final StringPath tag = createString("tag");

    public final NumberPath<Long> way = createNumber("way", Long.class);

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QRecipe(String variable) {
        super(Recipe.class, forVariable(variable));
    }

    public QRecipe(Path<? extends Recipe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipe(PathMetadata metadata) {
        super(Recipe.class, metadata);
    }

}

