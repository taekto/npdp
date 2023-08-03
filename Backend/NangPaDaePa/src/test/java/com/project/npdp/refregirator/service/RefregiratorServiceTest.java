package com.project.npdp.refregirator.service;

import com.project.npdp.refregirator.dto.request.IngredientFindRequestDto;
import com.project.npdp.refregirator.dto.response.IngredientFindResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RefregiratorServiceTest {

    @Autowired
    private RefregiratorService refregiratorService;
    // 재료 텍스트 조회
    @Test
    public void findIngredientByKor() {
        IngredientFindRequestDto ingredientFindRequestDto = new IngredientFindRequestDto("사과");

        List<IngredientFindResponseDto> ingredientByKor = refregiratorService.findIngredientByKor(ingredientFindRequestDto);
        log.info("ingredient 개수 = {}", ingredientByKor.size());
        Assertions.assertThat(ingredientByKor.size()).isEqualTo(14);
    }

}