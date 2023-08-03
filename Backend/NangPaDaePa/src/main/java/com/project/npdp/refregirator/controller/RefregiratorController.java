package com.project.npdp.refregirator.controller;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.service.RecipeService;
import com.project.npdp.refregirator.dto.request.*;
import com.project.npdp.refregirator.dto.response.IngredientFindResponseDto;
import com.project.npdp.refregirator.dto.response.SeasoningFindResponseDto;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.refregirator.service.RefregiratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/refregirator")
public class RefregiratorController {

    private final RefregiratorService refregiratorService;

    // 재료삭제
    @DeleteMapping("/modify/ingredient/{memberId}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable("memberId") Long memberId, RefregiratorModifyIngredientRequestDto refregiratorModifyIngredientRequestDto) {
        refregiratorService.deleteRecipeById(memberId, refregiratorModifyIngredientRequestDto);
        return ResponseEntity.ok().body(ResponseEntity.ok());
    }

    // 양념삭제

    // 재료 텍스트 조회
    @GetMapping("/search/ingredient")
    public ResponseEntity<?> findIngredientByKor(@RequestBody IngredientFindRequestDto ingredientFindRequestDto) {
        List<IngredientFindResponseDto> result = refregiratorService.findIngredientByKor(ingredientFindRequestDto);
        return ResponseEntity.ok().body(ResponseEntity.ok().body(result));
    }

    // 양념 텍스트 조회
    @GetMapping("/search/seasoning")
    public ResponseEntity<?> findIngredientByKor(@RequestBody SeasoningFindRequestDto seasoningFindRequestDto) {
        List<SeasoningFindResponseDto> result = refregiratorService.findSeasoningByKor(seasoningFindRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 회원 재료 입력
    @PostMapping("/member/ingredient/{memberId}")
    public ResponseEntity<?> memberSaveIngredient(@PathVariable("memberId") Long memberId, @RequestBody MemberIngredientSaveRequestDto memberIngredientSaveRequestDto) {
        refregiratorService.memberSaveIngredient(memberId, memberIngredientSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    // 회원 양념 입력
    @PostMapping("/member/seasoning/{memberId}")
    public ResponseEntity<?> memberSaveIngredient(@PathVariable("memberId") Long memberId, @RequestBody MemberSeasoningSaveRequestDto memberSeasoningSaveRequestDto) {
        refregiratorService.memberSaveIngredient(memberId, memberSeasoningSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    //회원 재료 조회
    @GetMapping("/ingredient/{memberId}")
    public ResponseEntity<?> findMemberIngredient(@PathVariable("memberId") Long memberId) {
        List<Refregirator> result = refregiratorService.findMemberIngredient(memberId);
        return ResponseEntity.ok().body(result);
    }
}
