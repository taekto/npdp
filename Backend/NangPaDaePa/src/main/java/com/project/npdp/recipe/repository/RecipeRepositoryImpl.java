package com.project.npdp.recipe.repository;

import com.project.npdp.food.entity.QIngredient;
import com.project.npdp.food.entity.QSeasoning;
import com.project.npdp.recipe.dto.response.*;
import com.project.npdp.recipe.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
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
    public RecipeDetailResponseDto findRecipeDetail(Long recipeId) {
        QRecipeIngredient recipeIngredient = QRecipeIngredient.recipeIngredient;
        QIngredient qIngredient = QIngredient.ingredient;
        QSeasoning qSeasoning = QSeasoning.seasoning;
        QRecipeSeasoning recipeSeasoning = QRecipeSeasoning.recipeSeasoning;
        QRecipeSequence recipeSequence = QRecipeSequence.recipeSequence;
        QRecipe recipe = QRecipe.recipe;


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
                .build();
    }

}
