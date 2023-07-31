package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSeasoning is a Querydsl query type for MemberSeasoning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSeasoning extends EntityPathBase<MemberSeasoning> {

    private static final long serialVersionUID = -1033856080L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSeasoning memberSeasoning = new QMemberSeasoning("memberSeasoning");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final com.project.npdp.food.entity.QSeasoning seasoning;

    public final NumberPath<Long> storage = createNumber("storage", Long.class);

    public QMemberSeasoning(String variable) {
        this(MemberSeasoning.class, forVariable(variable), INITS);
    }

    public QMemberSeasoning(Path<? extends MemberSeasoning> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSeasoning(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSeasoning(PathMetadata metadata, PathInits inits) {
        this(MemberSeasoning.class, metadata, inits);
    }

    public QMemberSeasoning(Class<? extends MemberSeasoning> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.seasoning = inits.isInitialized("seasoning") ? new com.project.npdp.food.entity.QSeasoning(forProperty("seasoning")) : null;
    }

}

