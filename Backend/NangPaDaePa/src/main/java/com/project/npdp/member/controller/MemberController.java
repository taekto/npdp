package com.project.npdp.member.controller;

import com.project.npdp.member.dto.request.MemberJoinRequestDto;
import com.project.npdp.member.dto.request.MemberLoginRequestDto;
import com.project.npdp.member.dto.response.MemberLoginResponseDto;
import com.project.npdp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto){
        memberService.join(memberJoinRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그인
    @PostMapping("/localLogin")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        MemberLoginResponseDto memberLoginInfo = memberService.login(memberLoginRequestDto);
        if(memberLoginInfo != null){
            return ResponseEntity.ok().body(memberLoginInfo);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 인증 실패");
        }
    }

    // 회원 상세조회
    @GetMapping("/{memberId}")
    public ResponseEntity<?> detail(@PathVariable Long memberId){
        return ResponseEntity.ok().body(memberService.findMemberById(memberId));
    }

//    @PostMapping("/write")
//    public ResponseEntity<String> writeSample(Authentication authentication){
//        return ResponseEntity.ok().body(authentication.getName() + "님의 글 등록 완료");
//    }

}
