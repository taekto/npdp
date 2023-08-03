package com.project.npdp.refregirator.controller;

import com.project.npdp.refregirator.dto.request.IngredientFindRequestDto;
import com.project.npdp.refregirator.dto.response.IngredientFindResponseDto;
import com.project.npdp.refregirator.service.RefregiratorService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class RefregiratorControllerTest {

    private final RefregiratorService refregiratorService;
    // 재료 텍스트 조회
    @Test
    @Transactional
    public void findIngredientByKor() {
        IngredientFindRequestDto ingredientFindRequestDto = new IngredientFindRequestDto("사과");

        List<IngredientFindResponseDto> ingredientByKor = refregiratorService.findIngredientByKor(ingredientFindRequestDto);
        Assertions.assertThat(ingredientByKor.size()).isEqualTo(14);
    }

}