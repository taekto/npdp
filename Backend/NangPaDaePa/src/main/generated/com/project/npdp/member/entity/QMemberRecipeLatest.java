package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRecipeLatest is a Querydsl query type for MemberRecipeLatest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRecipeLatest extends EntityPathBase<MemberRecipeLatest> {

    private static final long serialVersionUID = 1480014724L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRecipeLatest memberRecipeLatest = new QMemberRecipeLatest("memberRecipeLatest");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final com.project.npdp.recipe.entity.QRecipe recipe;

    public QMemberRecipeLatest(String variable) {
        this(MemberRecipeLatest.class, forVariable(variable), INITS);
    }

    public QMemberRecipeLatest(Path<? extends MemberRecipeLatest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRecipeLatest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRecipeLatest(PathMetadata metadata, PathInits inits) {
        this(MemberRecipeLatest.class, metadata, inits);
    }

    public QMemberRecipeLatest(Class<? extends MemberRecipeLatest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.project.npdp.recipe.entity.QRecipe(forProperty("recipe")) : null;
    }

}

