package com.project.npdp.snslogin.controller;


import com.project.npdp.member.entity.Member;
import com.project.npdp.snslogin.dto.GoogleTokenInfo;
import com.project.npdp.snslogin.dto.NaverTokenInfo;
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
import java.net.http.HttpResponse;

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

    @GetMapping("")
    public String testSnSlogin(){
        return "로그인 성공";
    }

    // 카카오 서버로부터 받은 CODE 정보
    @GetMapping("/kakao")
    public @ResponseBody Member kakaoCallback(String code){
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        String accessToken = kakaoLoginService.getAccessToken(code);
//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member memberInfo = kakaoLoginService.getMemberInfo(accessToken);

        return memberInfo;
    }

    @GetMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin() {
        try {
            String authorizeUrl = kakaoLoginService.getKakaoAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Kakao login URL.");
        }
    }

    // 구글 서버로부터 받은 CODE 정보
    @GetMapping("/google")
    public @ResponseBody String googleCallback(String code){
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        GoogleTokenInfo googleTokenInfo = googleLoginService.getGoogleToken(code);
        log.info(String.format("google-tokens: %s", googleTokenInfo.toString()));

//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member memberInfo = googleLoginService.getMemberInfo(googleTokenInfo);

        return null;
    }

    @GetMapping("/google-login")
    public ResponseEntity<?> googleLogin() {
        try {
            String authorizeUrl = googleLoginService.getGoogleAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Google login URL.");
        }
    }

    @GetMapping("/naver")
    public @ResponseBody String naverCallback(String code){
//         1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        NaverTokenInfo naverTokenInfo = naverLoginService.getNaverToken(code);
        log.info(String.format("naver-tokens: %s", naverTokenInfo.toString()));

//        2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
        Member memberInfo = naverLoginService.getMemberInfo(naverTokenInfo);

        return naverTokenInfo.toString();
    }

    @GetMapping("/naver-login")
    public ResponseEntity<?> naverLogin() {
        try {
            String authorizeUrl = naverLoginService.getNaverAuthorizeUrl();
            return ResponseEntity.status(302).header("Location", authorizeUrl).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Failed to generate Naver login URL.");
        }
    }
}