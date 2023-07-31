package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberAllergy is a Querydsl query type for MemberAllergy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberAllergy extends EntityPathBase<MemberAllergy> {

    private static final long serialVersionUID = -2103439023L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberAllergy memberAllergy = new QMemberAllergy("memberAllergy");

    public final com.project.npdp.domain.QAllergy allergy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QMemberAllergy(String variable) {
        this(MemberAllergy.class, forVariable(variable), INITS);
    }

    public QMemberAllergy(Path<? extends MemberAllergy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberAllergy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberAllergy(PathMetadata metadata, PathInits inits) {
        this(MemberAllergy.class, metadata, inits);
    }

    public QMemberAllergy(Class<? extends MemberAllergy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.allergy = inits.isInitialized("allergy") ? new com.project.npdp.domain.QAllergy(forProperty("allergy")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

