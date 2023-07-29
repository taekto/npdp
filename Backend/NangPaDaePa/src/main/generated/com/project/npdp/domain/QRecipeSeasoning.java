package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeSeasoning is a Querydsl query type for RecipeSeasoning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeSeasoning extends EntityPathBase<RecipeSeasoning> {

    private static final long serialVersionUID = -1753645115L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeSeasoning recipeSeasoning = new QRecipeSeasoning("recipeSeasoning");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRecipe recipe;

    public final QSeasoning seasoning;

    public QRecipeSeasoning(String variable) {
        this(RecipeSeasoning.class, forVariable(variable), INITS);
    }

    public QRecipeSeasoning(Path<? extends RecipeSeasoning> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeSeasoning(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeSeasoning(PathMetadata metadata, PathInits inits) {
        this(RecipeSeasoning.class, metadata, inits);
    }

    public QRecipeSeasoning(Class<? extends RecipeSeasoning> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new QRecipe(forProperty("recipe")) : null;
        this.seasoning = inits.isInitialized("seasoning") ? new QSeasoning(forProperty("seasoning")) : null;
    }

}

