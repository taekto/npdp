package com.project.npdp.member.controller;

import com.project.npdp.member.dto.request.*;
import com.project.npdp.member.dto.response.MemberAllergyResponseDto;
import com.project.npdp.member.dto.response.MemberLoginResponseDto;
import com.project.npdp.member.dto.response.MemberDetailResponseDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberAllergy;
import com.project.npdp.member.entity.MemberUtensil;
import com.project.npdp.member.entity.Role;
import com.project.npdp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
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

    // 비밀번호 확인
    @PostMapping("/checkPassword")
    public ResponseEntity<?> checkPw(@RequestBody MemberCheckPwRequestDto memberCheckPwRequestDto){
        System.out.println("memberPwRequestDto.password: " + memberCheckPwRequestDto.getPassword());
        try {
            memberService.checkPw(memberCheckPwRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 확인 실패");
        }
    }

    // 단순 비밀번호 변경
    @PutMapping("/updatePassword")
    public ResponseEntity<?> modifyPw(@RequestBody MemberModifyPwRequestDto memberModifyPwRequestDto){
        String email = memberModifyPwRequestDto.getEmail();
        String newPw = memberModifyPwRequestDto.getNewPassword();
        try {
            memberService.modifyPw(email, newPw);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 변경 실패");
        }
    }

    // 회원 상세조회
    @GetMapping("/{memberId}")
    public ResponseEntity<?> detail(@PathVariable("memberId") Long memberId){
//        MemberDetailResponseDto memberDetail = memberService.findMemberById(memberId);
//        return ResponseEntity.ok().body(ResponseEntity.ok().body(memberDetail));
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    // 회원 닉네임 변경
    @PutMapping("/nickname")
    public ResponseEntity<?> modifyNickname(@RequestBody MemberNicknameRequestDto memberNicknameRequestDto){
        try{
            memberService.modifyNickname(memberNicknameRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("닉네임 변경 실패");
        }
    }

    // 회원 성별 변경
    @PutMapping("/gender")
    public ResponseEntity<?> modifyGender(@RequestBody MemberGenderRequestDto memberGenderRequestDto){
        try{
            memberService.modifyGender(memberGenderRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("성별 변경 실패");
        }
    }

    // 회원 생일 변경
    @PutMapping("/birth")
    public ResponseEntity<?> modifyBirth(@RequestBody MemberBirthRequestDto memberBirthRequestDto){
        try{
            memberService.modifyBirth(memberBirthRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("생일 변경 실패");
        }
    }

    // 회원 정보 전체 수정
    @PutMapping("/modifyAll")
    public ResponseEntity<?> modifyMemberInfo(@RequestBody MemberModifyAllRequestDto memberModifyAllRequestDto){
        try {
            memberService.modifyAll(memberModifyAllRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원 수정 실패");
        }
    }

    // 회원 알러지 입력
    @PostMapping("/memberAllergy")
    public ResponseEntity<?> insertMemberAllergy(@RequestBody MemberAllergyRequestDto memberAllergyRequestDto){
        try{
            memberService.insertMemberAllergy(memberAllergyRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("알러지 입력 실패");
        }
    }

    // 회원 알러지 조회
    @GetMapping("/memberAllergy/{memberId}")
    public ResponseEntity<?> getMemberAllergy(@PathVariable Long memberId){
        try{
            return ResponseEntity.ok(memberService.getMemberAllergy(memberId));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("알러지 조회 실패");
        }
    }
    
    // 회원 비선호 재료 입력
    @PostMapping("/dislikeIngredient")
    public ResponseEntity<?> insertDislikeIngredient(@RequestBody MemberDislikeRequestDto memberDislikeRequestDto){
        try{
            memberService.insertDislikeIngredient(memberDislikeRequestDto);
            return ResponseEntity.ok("비선호 재료가 저장되었습니다.");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비선호 재료 입력 실패");
        }
    }
    
    // 회원 비선호 재료 조회
    @GetMapping("/dislikeIngredient/{memberId}")
    public ResponseEntity<?> getDislikeIngredient(@PathVariable Long memberId){
        try{
            return ResponseEntity.ok(memberService.getDislikeIngredient(memberId));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비선호 재료 조회 실패");
        }
    }

    // 회원 조리도구 입력
    @PostMapping("/memberUtensil")
    public ResponseEntity<?> insertMemberUtensil(@RequestBody MemberUtensilRequestDto memberUtensilRequestDto){
        try{
            memberService.insertMemberUtensil(memberUtensilRequestDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("조리도구 입력 실패");
        }
    }

    // 회원 조리도구 조회
    @GetMapping("/memberUtensil/{memberId}")
    public ResponseEntity<?> getMemberUtensil(@PathVariable Long memberId){
        try{
            return ResponseEntity.ok(memberService.getMemberUtensil(memberId));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("조리도구 조회 실패");
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId){
        try{
            memberService.deleteMember(memberId);
            return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("회원 탈퇴 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/write")
//    public ResponseEntity<String> writeSample(Authentication authentication){
//        return ResponseEntity.ok().body(authentication.getName() + "님의 글 등록 완료");
//    }

}
