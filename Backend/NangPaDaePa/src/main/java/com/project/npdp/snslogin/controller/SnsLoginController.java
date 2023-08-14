package com.project.npdp.snslogin.controller;


import com.project.npdp.member.entity.Member;
import com.project.npdp.member.service.MemberService;
import com.project.npdp.snslogin.dto.response.GoogleToken;
import com.project.npdp.snslogin.dto.response.KakaoToken;
import com.project.npdp.snslogin.dto.response.NaverToken;
import com.project.npdp.snslogin.dto.response.SnsLoginResponseDto;
import com.project.npdp.snslogin.service.GoogleLoginService;
import com.project.npdp.snslogin.service.KakaoLoginService;
import com.project.npdp.snslogin.service.NaverLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
@CrossOrigin(origins = "https://i9b202.p.ssafy.io")
@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
@Slf4j
public class SnsLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @Autowired
    private GoogleLoginService googleLoginService;

    @Autowired
    private NaverLoginService naverLoginService;

    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public String testSnSlogin(){
        return "로그인 성공";
    }

    // 카카오 서버로부터 받은 CODE 정보
    @GetMapping("/kakao")
    public @ResponseBody ResponseEntity<?> kakaoCallback(String code){
        log.info("kakao서버로부터 받음 code: "+code);
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        KakaoToken kakaoToken = kakaoLoginService.getToken(code);

        log.info("kakao access token: "+kakaoToken);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = kakaoLoginService.getMemberInfo(kakaoToken);

        try{
            SnsLoginResponseDto result = memberService.snsLogin(member);
            log.info("kakao 로그인 성공~~~~~~~~");
            return ResponseEntity.status(result.getHttpStatus()).body(result.getMemberLoginResponseDto()); // 201 Created + Member 객체 반환
        } catch (IllegalStateException e) {
            log.error("에러 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 회원입니다."); // 409 Conflict + 에러 메시지 반환
        }
    }

    @GetMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin() {
        log.info("kakao-login");
        try {
            String authorizeUrl = kakaoLoginService.getAuthorizeUrl();
//            return ResponseEntity.status(302).header("Access-Control-Allow-Origin", "https://i9b202.p.ssafy.io").header("Location", authorizeUrl).build();
            return ResponseEntity.status(200).body(authorizeUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Kakao login URL.");
        }
    }

    // 구글 서버로부터 받은 CODE 정보
    @GetMapping("/google")
    public @ResponseBody ResponseEntity<?> googleCallback(String code){
        log.info("구글 서버로부터 받은 code: "+code);
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        GoogleToken googleToken = googleLoginService.getToken(code);

        log.info("구글 서버로부터 받은 accessToken: "+googleToken);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = googleLoginService.getMemberInfo(googleToken);

        log.info("사용자 정보: ");
        printMember(member);

        try{
            SnsLoginResponseDto result = memberService.snsLogin(member);
            log.info("google 로그인 성공~~~~~~~~");
            return ResponseEntity.status(result.getHttpStatus()).body(result.getMemberLoginResponseDto()); // 201 Created + Member 객체 반환
        } catch (IllegalStateException e) {
            log.error("에러 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 회원입니다."); // 409 Conflict + 에러 메시지 반환
        }
    }

    @GetMapping("/google-login")
    public ResponseEntity<?> googleLogin() {
        log.info("google-login");
        try {
            String authorizeUrl = googleLoginService.getAuthorizeUrl();
//            return ResponseEntity.status(302).header("Access-Control-Allow-Origin", "https://i9b202.p.ssafy.io").header("Location", authorizeUrl).build();
            return ResponseEntity.status(200).body(authorizeUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Google login URL.");
        }
    }

    @GetMapping("/naver")
    public @ResponseBody ResponseEntity<?> naverCallback(String code){
        log.info("네이버로 부터 받은 code: "+code);
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        NaverToken naverToken = naverLoginService.getToken(code);
        log.info("네이버로부터 받은 액세스 토큰: "+naverToken);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = naverLoginService.getMemberInfo(naverToken);
        log.info("사용자 정보");
        printMember(member);
        try{
            SnsLoginResponseDto result = memberService.snsLogin(member);
            log.info("naver 로그인 성공~~~~~~~~");
            return ResponseEntity.status(result.getHttpStatus()).body(result.getMemberLoginResponseDto()); // 201 Created + Member 객체 반환
        } catch (IllegalStateException e) {
            log.error("에러 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 회원입니다."); // 409 Conflict + 에러 메시지 반환
        }
    }

    @GetMapping("/naver-login")
    public ResponseEntity<?> naverLogin() {
        log.info("naver-login");
        try {
            String authorizeUrl = naverLoginService.getAuthorizeUrl();
//            return ResponseEntity.status(302).header("Access-Control-Allow-Origin", "https://i9b202.p.ssafy.io").header("Location", authorizeUrl).build();
            return ResponseEntity.status(200).body(authorizeUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Naver login URL.");
        }
    }
    public void printMember(Member member){
        log.info("Member 정보:");
        log.info("ID: {}", member.getId());
        log.info("Email: {}", member.getEmail());
        log.info("Nickname: {}", member.getNickname());
        log.info("Password: {}", member.getPassword());
        log.info("Birth: {}", member.getBirth());
        log.info("Gender: {}", member.getGender());
        log.info("OAuth Type: {}", member.getOauth());
        log.info("Role: {}", member.getRole());
    }
}
