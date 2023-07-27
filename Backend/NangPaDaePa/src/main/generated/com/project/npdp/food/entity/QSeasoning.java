package com.project.npdp.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeasoning is a Querydsl query type for Seasoning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeasoning extends EntityPathBase<Seasoning> {

    private static final long serialVersionUID = 1361947814L;

    public static final QSeasoning seasoning = new QSeasoning("seasoning");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.npdp.member.entity.MemberSeasoning, com.project.npdp.member.entity.QMemberSeasoning> memberSeasoningList = this.<com.project.npdp.member.entity.MemberSeasoning, com.project.npdp.member.entity.QMemberSeasoning>createList("memberSeasoningList", com.project.npdp.member.entity.MemberSeasoning.class, com.project.npdp.member.entity.QMemberSeasoning.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<com.project.npdp.recipe.entity.RecipeSeasoning, com.project.npdp.recipe.entity.QRecipeSeasoning> recipeIngredientList = this.<com.project.npdp.recipe.entity.RecipeSeasoning, com.project.npdp.recipe.entity.QRecipeSeasoning>createList("recipeIngredientList", com.project.npdp.recipe.entity.RecipeSeasoning.class, com.project.npdp.recipe.entity.QRecipeSeasoning.class, PathInits.DIRECT2);

    public QSeasoning(String variable) {
        super(Seasoning.class, forVariable(variable));
    }

    public QSeasoning(Path<? extends Seasoning> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeasoning(PathMetadata metadata) {
        super(Seasoning.class, metadata);
    }

}

