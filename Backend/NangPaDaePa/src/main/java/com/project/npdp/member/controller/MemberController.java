package com.project.npdp.member.controller;

import com.project.npdp.member.dto.MemberJoinRequestDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto){
        memberService.join(memberJoinRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // 로그인
    @PostMapping("/localLogin")
    public ResponseEntity<String> login(String email, String password){
        return ResponseEntity.ok().body(memberService.login(email, password));
    }

//    // 비밀번호 변경
//    @PutMapping("/password/${memberId}")
//    public ResponseEntity<?> modifyPw(@PathVariable Long memberId, String password){
//        memberService.modifyPw(memberId, password);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

//    @PostMapping("/write")
//    public ResponseEntity<String> writeSample(Authentication authentication){
//        return ResponseEntity.ok().body(authentication.getName() + "님의 글 등록 완료");
//    }

}
