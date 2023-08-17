package com.project.npdp.member.controller;

import com.project.npdp.member.dto.request.MemberLatestRequestDto;
import com.project.npdp.member.dto.response.MemberLatestResponseDto;
import com.project.npdp.member.service.MemberLatestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members/latest")
@RequiredArgsConstructor
@Slf4j
public class MemberLatestController {

    private final MemberLatestService memberLatestService;

    @PostMapping("")
    public ResponseEntity<?> memberRecipeLatestInsert(@RequestBody MemberLatestRequestDto memberLatestRequestDto) {
        memberLatestService.memberRecipeLatestInsert(memberLatestRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> findMemberRecipeLatest(@PathVariable("memberId") Long memberId) {
        List<MemberLatestResponseDto> result = memberLatestService.findMemberRecipeLatest(memberId);
        return ResponseEntity.ok().body(result);
    }

}
