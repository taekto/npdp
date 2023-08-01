package com.project.npdp.refregirator.controller;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.service.RecipeService;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.refregirator.service.RefregiratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/refregirator")
public class RefregiratorController {

    private final RefregiratorService refregiratorService;

    // 재료삭제
    @DeleteMapping("/modify/ingredient/{memberId}")
    public ResponseEntity<?> findRecipeById(@PathVariable("memberId") Long memberId) {
        refregiratorService.deleteRecipeById(memberId);
        return ResponseEntity.ok().body(ResponseEntity.ok());
    }

    // 양념삭제

}
