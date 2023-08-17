package com.project.npdp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRecommend is a Querydsl query type for MemberRecommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRecommend extends EntityPathBase<MemberRecommend> {

    private static final long serialVersionUID = -1041093930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRecommend memberRecommend = new QMemberRecommend("memberRecommend");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.npdp.member.entity.QMember member;

    public final com.project.npdp.recipe.entity.QRecipe recipe;

    public final NumberPath<Double> similarity = createNumber("similarity", Double.class);

    public QMemberRecommend(String variable) {
        this(MemberRecommend.class, forVariable(variable), INITS);
    }

    public QMemberRecommend(Path<? extends MemberRecommend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRecommend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRecommend(PathMetadata metadata, PathInits inits) {
        this(MemberRecommend.class, metadata, inits);
    }

    public QMemberRecommend(Class<? extends MemberRecommend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.project.npdp.member.entity.QMember(forProperty("member")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.project.npdp.recipe.entity.QRecipe(forProperty("recipe")) : null;
    }

}

