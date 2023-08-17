package com.project.npdp.member.service;

import com.project.npdp.member.dto.response.AllergyResponseDto;
import com.project.npdp.member.repository.MemberAllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergyService {

    private final MemberAllergyRepository memberAllergyRepository;

    public List<AllergyResponseDto> findAllergyAll() {
        List<AllergyResponseDto> result = memberAllergyRepository.findAllergyAll();
        return result;
    }

}
