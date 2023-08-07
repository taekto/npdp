package com.project.npdp.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientSynonym is a Querydsl query type for IngredientSynonym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientSynonym extends EntityPathBase<IngredientSynonym> {

    private static final long serialVersionUID = -628360079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientSynonym ingredientSynonym = new QIngredientSynonym("ingredientSynonym");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIngredient ingredient;

    public final StringPath word = createString("word");

    public QIngredientSynonym(String variable) {
        this(IngredientSynonym.class, forVariable(variable), INITS);
    }

    public QIngredientSynonym(Path<? extends IngredientSynonym> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientSynonym(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientSynonym(PathMetadata metadata, PathInits inits) {
        this(IngredientSynonym.class, metadata, inits);
    }

    public QIngredientSynonym(Class<? extends IngredientSynonym> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
    }

}

