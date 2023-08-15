package com.project.npdp.recipe.repository;

import com.project.npdp.domain.QRecipeRecommend;
import com.project.npdp.domain.RecipeRecommend;
import com.project.npdp.food.entity.QIngredient;
import com.project.npdp.food.entity.QSeasoning;
import com.project.npdp.member.entity.MemberRecipeLike;
import com.project.npdp.member.entity.QMemberRecipeLike;
import com.project.npdp.recipe.dto.request.FindAllRecipeWithConditionRequestDto;
import com.project.npdp.recipe.dto.request.RecipeDetailRequestDto;
import com.project.npdp.recipe.dto.request.RecipeRecommendRequestDto;
import com.project.npdp.recipe.dto.response.*;
import com.project.npdp.recipe.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.npdp.domain.QRecipeRecommend.recipeRecommend;
import static com.project.npdp.member.entity.QMemberRecipeLike.memberRecipeLike;
import static com.project.npdp.recipe.entity.QRecipe.recipe;
import static com.project.npdp.recipe.entity.QRecipeIngredient.recipeIngredient;
import static com.project.npdp.recipe.entity.QRecipeSeasoning.recipeSeasoning;


@RequiredArgsConstructor
@Slf4j
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Recipe> findRecipeByContent(String content) {
        QRecipe recipe = QRecipe.recipe;

        List<Recipe> wantRecipeList = queryFactory
                .selectFrom(recipe)
                .where(recipe.name.contains(content))
                .fetch();

        return wantRecipeList;
    }

    @Override
    public RecipeDetailResponseDto findRecipeDetail(RecipeDetailRequestDto recipeDetailRequestDto) {
        QRecipeIngredient recipeIngredient = QRecipeIngredient.recipeIngredient;
        QIngredient qIngredient = QIngredient.ingredient;
        QSeasoning qSeasoning = QSeasoning.seasoning;
        QRecipeSeasoning recipeSeasoning = QRecipeSeasoning.recipeSeasoning;
        QRecipeSequence recipeSequence = QRecipeSequence.recipeSequence;
        QRecipe recipe = QRecipe.recipe;
        Long recipeId = recipeDetailRequestDto.getRecipeId();
        Long memberId = recipeDetailRequestDto.getMemberId();

        Long count = queryFactory
                .select(memberRecipeLike.recipe.id.count())
                .from(memberRecipeLike)
                .where(memberRecipeLike.recipe.id.eq(recipeId))
                .fetchOne();

        Boolean heartTF = false;

        MemberRecipeLike memberRecipeLike1 = queryFactory.select(memberRecipeLike)
                .from(memberRecipeLike)
                .where(memberRecipeLike.recipe.id.eq(recipeId)
                        .and(memberRecipeLike.member.id.eq(memberId)))
                .fetchOne();
        if(memberRecipeLike1 == null) {
            heartTF = false;
        }
        else {
            heartTF = true;
        }
        log.info("count : {} ", count);
        log.info("heartTF : {} ", heartTF);

        List<Tuple> result1 = queryFactory.select(
                        recipeIngredient.type,
                        recipeIngredient.amount,
                        recipeIngredient.unit,
                        qIngredient.code,
                        qIngredient.kor,
                        qIngredient.eng,
                        qIngredient.def,
                        qIngredient.level,
                        qIngredient.upperClass,
                        qIngredient.superUpperClass,
                        qIngredient.etc
                )
                .from(recipeIngredient)
                .leftJoin(recipeIngredient.ingredient, QIngredient.ingredient)
                .where(recipeIngredient.recipe.id.eq(recipeId))
                .fetch();

        List<RecipeIngredientDetailDto> recipeIngredients = new ArrayList<>();
        for (Tuple tuple : result1) {
            RecipeIngredientDetailDto recipeIngredientDetailDto = RecipeIngredientDetailDto.builder()
                    .type(tuple.get(recipeIngredient.type))
                    .amount(tuple.get(recipeIngredient.amount))
                    .unit(tuple.get(recipeIngredient.unit))
                    .ingredientId(tuple.get(qIngredient.id))
                    .code(tuple.get(qIngredient.code))
                    .kor(tuple.get(qIngredient.kor))
                    .eng(tuple.get(qIngredient.eng))
                    .def(tuple.get(qIngredient.def))
                    .level(tuple.get(qIngredient.level))
                    .upperClass(tuple.get(qIngredient.upperClass))
                    .superUpperClass(tuple.get(qIngredient.superUpperClass))
                    .etc(tuple.get(qIngredient.etc))
                    .build();

            recipeIngredients.add(recipeIngredientDetailDto);
        }

        List<Tuple> result2 = queryFactory
                .select(
                        recipeSeasoning.amount,
                        recipeSeasoning.unit,
                        qSeasoning.id,
                        qSeasoning.code,
                        qSeasoning.kor,
                        qSeasoning.eng,
                        qSeasoning.def,
                        qSeasoning.level,
                        qSeasoning.upperClass,
                        qSeasoning.superUpperClass,
                        qSeasoning.etc
                )
                .from(recipeSeasoning)
                .leftJoin(recipeSeasoning.seasoning, qSeasoning)
                .where(recipeSeasoning.recipe.id.eq(recipeId))
                .fetch();

        List<RecipeSeasoningDetailDto> recipeSeasonings = new ArrayList<>();
        for (Tuple tuple : result2) {
            RecipeSeasoningDetailDto recipeSeasoningDetailDto = RecipeSeasoningDetailDto.builder()
                    .amount(tuple.get(recipeSeasoning.amount))
                    .unit(tuple.get(recipeSeasoning.unit))
                    .seasoningId(tuple.get(qSeasoning.id))
                    .code(tuple.get(qSeasoning.code))
                    .kor(tuple.get(qSeasoning.kor))
                    .eng(tuple.get(qSeasoning.eng))
                    .def(tuple.get(qSeasoning.def))
                    .level(tuple.get(qSeasoning.level))
                    .upperClass(tuple.get(qSeasoning.upperClass))
                    .superUpperClass(tuple.get(qSeasoning.superUpperClass))
                    .etc(tuple.get(qSeasoning.etc))
                    .build();

            recipeSeasonings.add(recipeSeasoningDetailDto);
        }

        List<Tuple> result3 = queryFactory
                .select(
                        recipeSequence.sequence,
                        recipeSequence.description,
                        recipeSequence.img
                )
                .from(recipeSequence)
                .where(recipeSequence.recipe.id.eq(recipeId))
                .orderBy(recipeSequence.sequence.asc())
                .fetch();

        List<RecipeSequenceDetailDto> recipeSequences = new ArrayList<>();
        for (Tuple tuple : result3) {
            RecipeSequenceDetailDto recipeSequenceDetailDto = RecipeSequenceDetailDto.builder()
                    .sequence(tuple.get(recipeSequence.sequence))
                    .description(tuple.get(recipeSequence.description))
                    .img(tuple.get(recipeSequence.img))
                    .build();

            recipeSequences.add(recipeSequenceDetailDto);
        }

        Recipe recipeEntity = queryFactory.selectFrom(recipe)
                .where(recipe.id.eq(recipeId))
                .fetchOne();


        return RecipeDetailResponseDto.builder()
                .recipeId(recipeId)
                .name(recipeEntity.getName())
                .way(recipeEntity.getWay())
                .weight(recipeEntity.getWeight())
                .calorie(recipeEntity.getCalorie())
                .carbohydrate(recipeEntity.getCarbohydrate())
                .protein(recipeEntity.getProtein())
                .fat(recipeEntity.getFat())
                .salt(recipeEntity.getSalt())
                .imgSmall(recipeEntity.getImgSmall())
                .imgBig(recipeEntity.getImgBig())
                .category(recipeEntity.getCategory())
                .dish(recipeEntity.getDish())
                .recipeIngredients(recipeIngredients)
                .recipeSeasonings(recipeSeasonings)
                .recipeSequences(recipeSequences)
                .count(count)
                .heartTF(heartTF)
                .build();
    }

    @Override
    public List<RecipeResponseDto> findAllRecipeWithCategory(FindAllRecipeWithConditionRequestDto findAllRecipeWithConditionRequestDto) {
        QRecipe recipe = QRecipe.recipe; // QueryDSL을 위한 엔티티 경로
        String searchWord = findAllRecipeWithConditionRequestDto.getSearchWord();
        String classification = findAllRecipeWithConditionRequestDto.getClassification();
        String keyword = findAllRecipeWithConditionRequestDto.getKeyWord();

        log.info("searchWord ={}", searchWord);
        log.info("classification ={}", classification);
        log.info("keyword ={}", keyword);

        // 분류(전체) + 키워드
        if(classification.equals("전체")) {

            List<Recipe> result = queryFactory.selectFrom(recipe)
                    .where(keywordEq(keyword)).fetch();
            return result.stream()
                    .map(recipeEntity -> RecipeResponseDto.builder()
                            .recipeId(recipeEntity.getId())
                            .name(recipeEntity.getName())
                            .imgBig(recipeEntity.getImgBig())
                            .imgSmall(recipeEntity.getImgSmall())
                            .category(recipeEntity.getCategory())
                            .build())
                    .collect(Collectors.toList());
        }
        // 분류(레시피명) + 키워드
        else if(classification.equals("레시피명")) {
            List<Recipe> result = queryFactory.selectFrom(recipe)
                    .where(recipeNameContains(searchWord), keywordEq(keyword)).fetch();

            return result.stream()
                    .map(recipeEntity -> RecipeResponseDto.builder()
                            .recipeId(recipeEntity.getId())
                            .name(recipeEntity.getName())
                            .imgBig(recipeEntity.getImgBig())
                            .imgSmall(recipeEntity.getImgSmall())
                            .category(recipeEntity.getCategory())
                            .build())
                    .collect(Collectors.toList());
        }
        // 분류(주재료) + 키워드
        else if (classification.equals("주재료")) {
            List<RecipeIngredient> result = queryFactory.selectFrom(recipeIngredient)
                    .innerJoin(recipeIngredient.recipe, recipe).fetchJoin()
                    .innerJoin(recipeIngredient.ingredient, QIngredient.ingredient).fetchJoin()
                    .where(keywordEq(keyword), recipeIngredientTypeEq(0L), IngredientKorContains(searchWord))
                    .fetch();
            return result.stream()
                    .map(recipeIngredientEntity -> RecipeResponseDto.builder()
                            .recipeId(recipeIngredientEntity.getRecipe().getId())
                            .name(recipeIngredientEntity.getRecipe().getName()) // 수정된 부분
                            .imgBig(recipeIngredientEntity.getRecipe().getImgBig())
                            .imgSmall(recipeIngredientEntity.getRecipe().getImgSmall())
                            .category(recipeIngredientEntity.getRecipe().getCategory())
                            .build())
                    .collect(Collectors.toList());
        }
        // 분류(보조재료) + 키워드
        else if(classification.equals("보조재료")) {
            List<RecipeIngredient> result = queryFactory.selectFrom(recipeIngredient)
                    .innerJoin(recipeIngredient.recipe, recipe).fetchJoin()
                    .innerJoin(recipeIngredient.ingredient, QIngredient.ingredient).fetchJoin()
                    .where(keywordEq(keyword), recipeIngredientTypeEq(1L), IngredientKorContains(searchWord))
                    .fetch();
            return result.stream()
                    .map(recipeIngredientEntity -> RecipeResponseDto.builder()
                            .recipeId(recipeIngredientEntity.getRecipe().getId())
                            .name(recipeIngredientEntity.getRecipe().getName()) // 수정된 부분
                            .imgBig(recipeIngredientEntity.getRecipe().getImgBig())
                            .imgSmall(recipeIngredientEntity.getRecipe().getImgSmall())
                            .category(recipeIngredientEntity.getRecipe().getCategory())
                            .build())
                    .collect(Collectors.toList());
        }
        // 분류(양념) + 키워드
        else if(classification.equals("양념")) {
            List<RecipeSeasoning> result = queryFactory.selectFrom(recipeSeasoning)
                    .innerJoin(recipeSeasoning.recipe, recipe).fetchJoin()
                    .innerJoin(recipeSeasoning.seasoning, QSeasoning.seasoning).fetchJoin()
                    .where(SeasoningKorContains(searchWord), keywordEq(keyword))
                    .fetch();
            return result.stream()
                    .map(recipeSeasoningEntity -> RecipeResponseDto.builder()
                            .recipeId(recipeSeasoningEntity.getRecipe().getId())
                            .name(recipeSeasoningEntity.getRecipe().getName())
                            .imgBig(recipeSeasoningEntity.getRecipe().getImgBig())
                            .imgSmall(recipeSeasoningEntity.getRecipe().getImgSmall())
                            .category(recipeSeasoningEntity.getRecipe().getCategory())
                            .build())
                    .collect(Collectors.toList());
        }
        return null;

    }

    @Override
    public List<RecipeRecommendResponseDto> findRecipesWithSimilarity(RecipeRecommendRequestDto recipeRecommendRequestDto) {
        log.info("recipeRecommendRequestDto = {}", recipeRecommendRequestDto.getRecipeOwnId());
        Long recipeOwnId = recipeRecommendRequestDto.getRecipeOwnId();
        List<RecipeRecommend> result = queryFactory.selectFrom(recipeRecommend)
                .innerJoin(recipeRecommend.recipeSlave, recipe).fetchJoin()
                .innerJoin(recipeRecommend.recipeOwn, recipe).fetchJoin()
                .where(recipeRecommend.recipeOwn.id.eq(recipeOwnId))
                .orderBy(recipeRecommend.similarity.desc())
                .limit(20)
                .fetch();
        return result.stream()
                .map(recipeRecommendEntity -> RecipeRecommendResponseDto.builder()
                        .recipeId(recipeRecommendEntity.getRecipeSlave().getId())
                        .name(recipeRecommendEntity.getRecipeSlave().getName())
                        .imgBig(recipeRecommendEntity.getRecipeSlave().getImgBig())
                        .imgSmall(recipeRecommendEntity.getRecipeSlave().getImgSmall())
                        .category(recipeRecommendEntity.getRecipeSlave().getCategory())
                        .similarity(recipeRecommendEntity.getSimilarity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeHeartResponseDto> findTop20RecipesByRecipeIdCount() {
        List<Tuple> tuples = queryFactory
                .select(
                        memberRecipeLike.recipe.id,
                        memberRecipeLike.recipe.name,
                        memberRecipeLike.recipe.imgBig,
                        memberRecipeLike.recipe.imgSmall,
                        memberRecipeLike.recipe.category,
                        memberRecipeLike.recipe.id.count())
                .from(memberRecipeLike)
                .innerJoin(memberRecipeLike.recipe, recipe)
                .groupBy(memberRecipeLike.recipe.id)
                .orderBy(memberRecipeLike.recipe.id.count().desc())
                .limit(20)
                .fetch();

        List<RecipeHeartResponseDto> result = tuples.stream()
                .map(tuple -> {
                    Long recipeId = tuple.get(memberRecipeLike.recipe.id);
                    String name = tuple.get(memberRecipeLike.recipe.name);
                    String imgBig = tuple.get(memberRecipeLike.recipe.imgBig);
                    String imgSmall = tuple.get(memberRecipeLike.recipe.imgSmall);
                    String category = tuple.get(memberRecipeLike.recipe.category);
                    Long count = tuple.get(memberRecipeLike.recipe.id.count());

                    return RecipeHeartResponseDto.builder()
                            .recipeId(recipeId)
                            .name(name)
                            .imgBig(imgBig)
                            .imgSmall(imgSmall)
                            .category(category)
                            .count(count)
                            .build();
                })
                .collect(Collectors.toList());

        return result;
    }






    private BooleanExpression keywordEq(String keyword) {
        return keyword != null ? QRecipe.recipe.category.eq(keyword) : null;
    }

    private BooleanExpression recipeNameContains(String searchWord) {
        return searchWord != null ? QRecipe.recipe.name.contains(searchWord) : null;
    }

    private BooleanExpression recipeIngredientTypeEq(Long type) {
        return type != null ? QRecipeIngredient.recipeIngredient.type.eq(type) : null;
    }

    private BooleanExpression IngredientKorContains(String kor) {
        return kor != null ? QIngredient.ingredient.kor.contains(kor) : null;
    }

    private BooleanExpression SeasoningKorContains(String kor) {
        return kor != null ? QSeasoning.seasoning.kor.contains(kor) : null;
    }


}
