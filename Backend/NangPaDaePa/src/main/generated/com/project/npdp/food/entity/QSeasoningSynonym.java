package com.project.npdp.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeasoningSynonym is a Querydsl query type for SeasoningSynonym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeasoningSynonym extends EntityPathBase<SeasoningSynonym> {

    private static final long serialVersionUID = 331202677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasoningSynonym seasoningSynonym = new QSeasoningSynonym("seasoningSynonym");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSeasoning seasoning;

    public final StringPath word = createString("word");

    public QSeasoningSynonym(String variable) {
        this(SeasoningSynonym.class, forVariable(variable), INITS);
    }

    public QSeasoningSynonym(Path<? extends SeasoningSynonym> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSeasoningSynonym(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSeasoningSynonym(PathMetadata metadata, PathInits inits) {
        this(SeasoningSynonym.class, metadata, inits);
    }

    public QSeasoningSynonym(Class<? extends SeasoningSynonym> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasoning = inits.isInitialized("seasoning") ? new QSeasoning(forProperty("seasoning")) : null;
    }

}

