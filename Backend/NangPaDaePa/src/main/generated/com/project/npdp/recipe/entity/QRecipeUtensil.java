package com.project.npdp.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeUtensil is a Querydsl query type for RecipeUtensil
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeUtensil extends EntityPathBase<RecipeUtensil> {

    private static final long serialVersionUID = -2078474697L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeUtensil recipeUtensil = new QRecipeUtensil("recipeUtensil");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRecipe recipe;

    public final com.project.npdp.domain.QUtensil utensil;

    public QRecipeUtensil(String variable) {
        this(RecipeUtensil.class, forVariable(variable), INITS);
    }

    public QRecipeUtensil(Path<? extends RecipeUtensil> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeUtensil(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeUtensil(PathMetadata metadata, PathInits inits) {
        this(RecipeUtensil.class, metadata, inits);
    }

    public QRecipeUtensil(Class<? extends RecipeUtensil> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new QRecipe(forProperty("recipe")) : null;
        this.utensil = inits.isInitialized("utensil") ? new com.project.npdp.domain.QUtensil(forProperty("utensil")) : null;
    }

}

