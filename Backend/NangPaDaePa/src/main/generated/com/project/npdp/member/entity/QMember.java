package com.project.npdp.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 162219823L;

    public static final QMember member = new QMember("member1");

    public final StringPath birth = createString("birth");

    public final ListPath<com.project.npdp.domain.Calendar, com.project.npdp.domain.QCalendar> calendarList = this.<com.project.npdp.domain.Calendar, com.project.npdp.domain.QCalendar>createList("calendarList", com.project.npdp.domain.Calendar.class, com.project.npdp.domain.QCalendar.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MemberAllergy, QMemberAllergy> memberAllergyList = this.<MemberAllergy, QMemberAllergy>createList("memberAllergyList", MemberAllergy.class, QMemberAllergy.class, PathInits.DIRECT2);

    public final ListPath<MemberDislikeIngredient, QMemberDislikeIngredient> memberDislikeIngredientList = this.<MemberDislikeIngredient, QMemberDislikeIngredient>createList("memberDislikeIngredientList", MemberDislikeIngredient.class, QMemberDislikeIngredient.class, PathInits.DIRECT2);

    public final ListPath<MemberRecipeLatest, QMemberRecipeLatest> memberRecipeLatestList = this.<MemberRecipeLatest, QMemberRecipeLatest>createList("memberRecipeLatestList", MemberRecipeLatest.class, QMemberRecipeLatest.class, PathInits.DIRECT2);

    public final ListPath<MemberRecipeLike, QMemberRecipeLike> memberRecipeLikeList = this.<MemberRecipeLike, QMemberRecipeLike>createList("memberRecipeLikeList", MemberRecipeLike.class, QMemberRecipeLike.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.domain.MemberRecommend, com.project.npdp.domain.QMemberRecommend> memberRecommendList = this.<com.project.npdp.domain.MemberRecommend, com.project.npdp.domain.QMemberRecommend>createList("memberRecommendList", com.project.npdp.domain.MemberRecommend.class, com.project.npdp.domain.QMemberRecommend.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.refregirator.entity.MemberSeasoning, com.project.npdp.refregirator.entity.QMemberSeasoning> memberSeasoningList = this.<com.project.npdp.refregirator.entity.MemberSeasoning, com.project.npdp.refregirator.entity.QMemberSeasoning>createList("memberSeasoningList", com.project.npdp.refregirator.entity.MemberSeasoning.class, com.project.npdp.refregirator.entity.QMemberSeasoning.class, PathInits.DIRECT2);

    public final ListPath<MemberUtensil, QMemberUtensil> memberUtensilList = this.<MemberUtensil, QMemberUtensil>createList("memberUtensilList", MemberUtensil.class, QMemberUtensil.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final EnumPath<OAuthType> oauth = createEnum("oauth", OAuthType.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> quit = createDateTime("quit", java.time.LocalDateTime.class);

    public final ListPath<com.project.npdp.refregirator.entity.Refregirator, com.project.npdp.refregirator.entity.QRefregirator> refregiratorList = this.<com.project.npdp.refregirator.entity.Refregirator, com.project.npdp.refregirator.entity.QRefregirator>createList("refregiratorList", com.project.npdp.refregirator.entity.Refregirator.class, com.project.npdp.refregirator.entity.QRefregirator.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

