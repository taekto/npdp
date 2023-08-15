package com.project.npdp.refregirator.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefregirator is a Querydsl query type for Refregirator
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefregirator extends EntityPathBase<Refregirator> {

    private static final long serialVersionUID = 851407227L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefregirator refregirator = new QRefregirator("refregirator");

    public final StringPath amount = createString("amount");

    public final DateTimePath<java.time.LocalDateTime> expiredDate = createDateTime("expiredDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.npdp.food.entity.QIngredient ingredient;

    public final com.project.npdp.member.entity.QMember member;

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> storage = createNumber("storage", Long.class);

    public final StringPath unit = createString("unit");

    public QRefregirator(String variable) {
        this(Refregirator.class, forVariable(variable), INITS);
    }

    public QRefregirator(Path<? extends Refregirator> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefregirator(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefregirator(PathMetadata metadata, PathInits inits) {
        this(Refregirator.class, metadata, inits);
    }

    public QRefregirator(Class<? extends Refregirator> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new com.project.npdp.food.entity.QIngredient(forProperty("ingredient")) : null;
        this.member = inits.isInitialized("member") ? new com.project.npdp.member.entity.QMember(forProperty("member")) : null;
    }

}

