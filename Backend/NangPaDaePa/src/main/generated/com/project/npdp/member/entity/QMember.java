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

    public final ListPath<Calendar, QCalendar> calendarList = this.<Calendar, QCalendar>createList("calendarList", Calendar.class, QCalendar.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MemberAllergy, QMemberAllergy> memberAllergyList = this.<MemberAllergy, QMemberAllergy>createList("memberAllergyList", MemberAllergy.class, QMemberAllergy.class, PathInits.DIRECT2);

    public final ListPath<MemberDislikeIngredient, QMemberDislikeIngredient> memberDislikeIngredientList = this.<MemberDislikeIngredient, QMemberDislikeIngredient>createList("memberDislikeIngredientList", MemberDislikeIngredient.class, QMemberDislikeIngredient.class, PathInits.DIRECT2);

    public final ListPath<MemberRecipeLatest, QMemberRecipeLatest> memberRecipeLatestList = this.<MemberRecipeLatest, QMemberRecipeLatest>createList("memberRecipeLatestList", MemberRecipeLatest.class, QMemberRecipeLatest.class, PathInits.DIRECT2);

    public final ListPath<MemberRecipeLike, QMemberRecipeLike> memberRecipeLikeList = this.<MemberRecipeLike, QMemberRecipeLike>createList("memberRecipeLikeList", MemberRecipeLike.class, QMemberRecipeLike.class, PathInits.DIRECT2);

    public final ListPath<MemberSeasoning, QMemberSeasoning> memberSeasoningList = this.<MemberSeasoning, QMemberSeasoning>createList("memberSeasoningList", MemberSeasoning.class, QMemberSeasoning.class, PathInits.DIRECT2);

    public final ListPath<com.project.npdp.utensil.entity.MemberUtensil, com.project.npdp.utensil.entity.QMemberUtensil> memberUtensilList = this.<com.project.npdp.utensil.entity.MemberUtensil, com.project.npdp.utensil.entity.QMemberUtensil>createList("memberUtensilList", com.project.npdp.utensil.entity.MemberUtensil.class, com.project.npdp.utensil.entity.QMemberUtensil.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath oauth = createString("oauth");

    public final StringPath password = createString("password");

    public final ListPath<com.project.npdp.refrigerator.entity.Refregirator, com.project.npdp.refrigerator.entity.QRefregirator> refregiratorList = this.<com.project.npdp.refrigerator.entity.Refregirator, com.project.npdp.refrigerator.entity.QRefregirator>createList("refregiratorList", com.project.npdp.refrigerator.entity.Refregirator.class, com.project.npdp.refrigerator.entity.QRefregirator.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

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

