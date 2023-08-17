package com.project.npdp.member.controller;

import com.project.npdp.member.dto.response.AllergyResponseDto;
import com.project.npdp.member.service.AllergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allergy")
@Slf4j
public class AllergyController {

    private final AllergyService allergyService;

    @GetMapping("")
    public ResponseEntity<?> findAllergyAll() {
        List<AllergyResponseDto> result = allergyService.findAllergyAll();
        return ResponseEntity.ok(result);
    }

}
