package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.project.npdp.member.entity.MemberDislikeIngredient;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberDislikeIngredient is a Querydsl query type for MemberDislikeIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberDislikeIngredient extends EntityPathBase<MemberDislikeIngredient> {

    private static final long serialVersionUID = -1354596112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberDislikeIngredient memberDislikeIngredient = new QMemberDislikeIngredient("memberDislikeIngredient");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIngredient ingredient;

    public final QMember member;

    public QMemberDislikeIngredient(String variable) {
        this(MemberDislikeIngredient.class, forVariable(variable), INITS);
    }

    public QMemberDislikeIngredient(Path<? extends MemberDislikeIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberDislikeIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberDislikeIngredient(PathMetadata metadata, PathInits inits) {
        this(MemberDislikeIngredient.class, metadata, inits);
    }

    public QMemberDislikeIngredient(Class<? extends MemberDislikeIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

