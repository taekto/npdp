package com.project.npdp.member.controller;

import com.project.npdp.member.dto.MemberJoinRequestDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(MemberJoinRequestDto memberJoinRequestDto){
        return ResponseEntity.ok().body(memberService.join(memberJoinRequestDto));
    }


    // 로그인
    @PostMapping("/localLogin")
    public ResponseEntity<String> login(String email, String password){
        return ResponseEntity.ok().body(memberService.login(email, password));
    }

//    @PostMapping("/write")
//    public ResponseEntity<String> writeSample(Authentication authentication){
//        return ResponseEntity.ok().body(authentication.getName() + "님의 글 등록 완료");
//    }

}
