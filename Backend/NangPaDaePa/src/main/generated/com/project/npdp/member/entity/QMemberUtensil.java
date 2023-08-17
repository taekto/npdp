package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberUtensil is a Querydsl query type for MemberUtensil
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberUtensil extends EntityPathBase<MemberUtensil> {

    private static final long serialVersionUID = -1310396897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberUtensil memberUtensil = new QMemberUtensil("memberUtensil");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final com.project.npdp.domain.QUtensil utensil;

    public QMemberUtensil(String variable) {
        this(MemberUtensil.class, forVariable(variable), INITS);
    }

    public QMemberUtensil(Path<? extends MemberUtensil> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberUtensil(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberUtensil(PathMetadata metadata, PathInits inits) {
        this(MemberUtensil.class, metadata, inits);
    }

    public QMemberUtensil(Class<? extends MemberUtensil> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.utensil = inits.isInitialized("utensil") ? new com.project.npdp.domain.QUtensil(forProperty("utensil")) : null;
    }

}

