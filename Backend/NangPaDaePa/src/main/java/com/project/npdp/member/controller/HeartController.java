package com.project.npdp.member.controller;

import com.project.npdp.member.dto.request.MemberLikeRequestDto;
import com.project.npdp.member.service.HeartService;
import com.project.npdp.recipe.dto.response.RecipeHeartResponseDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("")
    public ResponseEntity<?> memberRecipeHeartInsert(@RequestBody MemberLikeRequestDto memberLikeRequestDto) {
        heartService.memberRecipeHeartInsert(memberLikeRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> memberRecipeHeartDelete(@RequestBody MemberLikeRequestDto memberLikeRequestDto) {
        heartService.memberRecipeHeartDelete(memberLikeRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> findMemberRecipeHeartById(@PathVariable("memberId") Long memberId) {
        List<RecipeResponseDto> result = heartService.findMemberRecipeHeartById(memberId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/count")
    public ResponseEntity<?> findTop20RecipesByRecipeIdCount() {
        List<RecipeHeartResponseDto> result = heartService.findTop20RecipesByRecipeIdCount();
        return ResponseEntity.ok().body(result);
    }

}
