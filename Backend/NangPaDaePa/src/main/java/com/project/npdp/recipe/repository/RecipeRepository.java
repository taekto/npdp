package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.dto.request.MemberRecommendRequestDto;
import com.project.npdp.recipe.dto.response.MemberRecommendResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {

    @Query(nativeQuery = true, value = "SELECT " +
            "    mr.recipe_id, " +
            "    r.name, " +
            "    r.img_big, " +
            "    r.img_small, " +
            "    r.category, " +
            "    mr.similarity " +
            "FROM " +
            "    member_recommend mr " +
            "JOIN " +
            "    recipe r ON mr.recipe_id = r.recipe_id " +
            "LEFT JOIN ( " +
            "    SELECT " +
            "        DISTINCT ri.recipe_id " +
            "    FROM " +
            "        recipe_ingredient ri " +
            "    JOIN " +
            "        ingredient i ON ri.ingredient_id = i.ingredient_id " +
            "    JOIN " +
            "        member_dislike_ingredient mdi ON mdi.member_id = :#{#memberRecommendRequestDto.memberId} " +
            "    WHERE " +
            "        mdi.ingredient_id = i.ingredient_id " +
            ") excluded_recipes ON r.recipe_id = excluded_recipes.recipe_id " +
            "WHERE " +
            "    mr.member_id = :#{#memberRecommendRequestDto.memberId} AND excluded_recipes.recipe_id IS NULL " +
            "ORDER BY " +
            "    mr.similarity DESC " +
            "LIMIT 20")
    List<MemberRecommendResponseVo> findMemberRecommendationsExcludingAllergies(@Param("memberRecommendRequestDto") MemberRecommendRequestDto memberRecommendRequestDto);

    interface MemberRecommendResponseVo {
        Long getrecipe_id();
        String getname();
        String getimg_big();
        String getimg_small();
        String getcategory();
        Double getsimilarity();
    }

}
