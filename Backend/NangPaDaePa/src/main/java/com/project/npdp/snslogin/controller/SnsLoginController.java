package com.project.npdp.snslogin.controller;


import com.project.npdp.snslogin.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
@Slf4j
public class SnsLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    // 카카오 서버로부터 받은 CODE 정보
    @GetMapping("/kakao")
    public @ResponseBody String kakaoCallback(String code){
        // 1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
        String accessToken = kakaoLoginService.getAccessToken(code);

        // 2. 응답을 콘솔과 브라우저에서 출력한다.
        log.info(accessToken);
        return accessToken;
    }

}
