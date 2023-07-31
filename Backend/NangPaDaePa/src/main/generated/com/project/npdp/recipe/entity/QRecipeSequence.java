package com.project.npdp.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeSequence is a Querydsl query type for RecipeSequence
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeSequence extends EntityPathBase<RecipeSequence> {

    private static final long serialVersionUID = 723089144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeSequence recipeSequence = new QRecipeSequence("recipeSequence");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final QRecipe recipe;

    public final NumberPath<Long> sequence = createNumber("sequence", Long.class);

    public QRecipeSequence(String variable) {
        this(RecipeSequence.class, forVariable(variable), INITS);
    }

    public QRecipeSequence(Path<? extends RecipeSequence> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeSequence(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeSequence(PathMetadata metadata, PathInits inits) {
        this(RecipeSequence.class, metadata, inits);
    }

    public QRecipeSequence(Class<? extends RecipeSequence> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new QRecipe(forProperty("recipe")) : null;
    }

}

