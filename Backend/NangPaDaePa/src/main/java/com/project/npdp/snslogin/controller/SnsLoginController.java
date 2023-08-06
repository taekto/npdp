package com.project.npdp.snslogin.controller;


import com.project.npdp.member.entity.Member;
import com.project.npdp.member.service.MemberService;
import com.project.npdp.snslogin.dto.GoogleToken;
import com.project.npdp.snslogin.dto.KakaoToken;
import com.project.npdp.snslogin.dto.NaverToken;
import com.project.npdp.snslogin.service.GoogleLoginService;
import com.project.npdp.snslogin.service.KakaoLoginService;
import com.project.npdp.snslogin.service.NaverLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

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
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        KakaoToken kakaoToken = kakaoLoginService.getToken(code);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = kakaoLoginService.getMemberInfo(kakaoToken);
        printMember(member);
        return ResponseEntity.ok().body(memberService.snsLogin(member));
    }

    @GetMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin() {
        try {
            String authorizeUrl = kakaoLoginService.getAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Kakao login URL.");
        }
    }

    // 구글 서버로부터 받은 CODE 정보
    @GetMapping("/google")
    public @ResponseBody ResponseEntity<?> googleCallback(String code){
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        GoogleToken googleToken = googleLoginService.getToken(code);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = googleLoginService.getMemberInfo(googleToken);
        printMember(member);

        return ResponseEntity.ok().body(memberService.snsLogin(member));
    }

    @GetMapping("/google-login")
    public ResponseEntity<?> googleLogin() {
        try {
            String authorizeUrl = googleLoginService.getAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Google login URL.");
        }
    }

    @GetMapping("/naver")
    public @ResponseBody ResponseEntity<?> naverCallback(String code){
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        NaverToken naverToken = naverLoginService.getToken(code);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member member = naverLoginService.getMemberInfo(naverToken);
        printMember(member);

        return ResponseEntity.ok().body(memberService.snsLogin(member));
    }

    @GetMapping("/naver-login")
    public ResponseEntity<?> naverLogin() {
        try {
            String authorizeUrl = naverLoginService.getAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
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
