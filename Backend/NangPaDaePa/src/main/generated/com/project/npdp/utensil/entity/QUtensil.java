package com.project.npdp.utensil.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUtensil is a Querydsl query type for Utensil
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUtensil extends EntityPathBase<Utensil> {

    private static final long serialVersionUID = 473923489L;

    public static final QUtensil utensil = new QUtensil("utensil");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MemberUtensil, QMemberUtensil> memberUtensilList = this.<MemberUtensil, QMemberUtensil>createList("memberUtensilList", MemberUtensil.class, QMemberUtensil.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<RecipeUtensil, QRecipeUtensil> recipeUtensilList = this.<RecipeUtensil, QRecipeUtensil>createList("recipeUtensilList", RecipeUtensil.class, QRecipeUtensil.class, PathInits.DIRECT2);

    public final NumberPath<Long> way = createNumber("way", Long.class);

    public QUtensil(String variable) {
        super(Utensil.class, forVariable(variable));
    }

    public QUtensil(Path<? extends Utensil> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUtensil(PathMetadata metadata) {
        super(Utensil.class, metadata);
    }

}

