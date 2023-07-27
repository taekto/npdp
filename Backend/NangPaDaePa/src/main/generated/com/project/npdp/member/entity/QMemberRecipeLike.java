package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRecipeLike is a Querydsl query type for MemberRecipeLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRecipeLike extends EntityPathBase<MemberRecipeLike> {

    private static final long serialVersionUID = 1431713492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRecipeLike memberRecipeLike = new QMemberRecipeLike("memberRecipeLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final com.project.npdp.recipe.entity.QRecipe recipe;

    public QMemberRecipeLike(String variable) {
        this(MemberRecipeLike.class, forVariable(variable), INITS);
    }

    public QMemberRecipeLike(Path<? extends MemberRecipeLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRecipeLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRecipeLike(PathMetadata metadata, PathInits inits) {
        this(MemberRecipeLike.class, metadata, inits);
    }

    public QMemberRecipeLike(Class<? extends MemberRecipeLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.project.npdp.recipe.entity.QRecipe(forProperty("recipe")) : null;
    }

}

