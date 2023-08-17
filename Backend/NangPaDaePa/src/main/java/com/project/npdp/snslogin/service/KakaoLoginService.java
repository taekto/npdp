package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.npdp.configuration.OAuthConfig;
import com.project.npdp.member.entity.Member;
import com.project.npdp.snslogin.dto.response.KakaoMember;
import com.project.npdp.snslogin.dto.response.KakaoToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@Slf4j
public class KakaoLoginService implements OAuthProviderService<KakaoToken> {

    private OAuthConfig oAuthConfig;

    @Autowired
    public KakaoLoginService(OAuthConfig oAuthConfig) {
        this.oAuthConfig = oAuthConfig;
    }

    @Override
    public String getAuthorizeUrl() throws UnsupportedEncodingException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(oAuthConfig.getKakaoAuthorizationUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", oAuthConfig.getKakaoClientId())
                .queryParam("redirect_uri", URLEncoder.encode(oAuthConfig.getKakaoRedirectUri(), "UTF-8"))
                .build();

        return uriComponents.toString();
    }

    @Override
    public KakaoToken getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        headers.add("Origin", "http://i9b202.p.ssafy.io/");
        headers.add("Access-Control-Allow-Origin", "https://i9b202.p.ssafy.io");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", oAuthConfig.getKakaoGrantType());
        body.add("client_id", oAuthConfig.getKakaoClientId());
        body.add("redirect_uri", oAuthConfig.getKakaoRedirectUri());
        body.add("code", code);
        body.add("client_secret", oAuthConfig.getKakaoClientSecret());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getKakaoTokenUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                KakaoToken kakaoToken = objectMapper.readValue(responseEntity.getBody(), KakaoToken.class);
                return kakaoToken;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.error("Kakao API returned an error: " + responseEntity.getBody());
        }

        return null;
    }

    @Override
    public Member getMemberInfo(KakaoToken kakaoToken){
        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoToken.getAccessToken());
        headers.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Origin", "http://i9b202.p.ssafy.io/");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(headers);

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getKakaoUserInfoUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 카카오 인증 서버가 리턴한 사용자 정보
        String memberInfo = responseEntity.getBody();
        try {
        // JSON 데이터에서 추출한 정보를 바탕으로 Member 객체 설정
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(memberInfo, JsonObject.class);
        JsonObject kakao_acoount = jsonObject.getAsJsonObject("kakao_account");
        JsonObject profile = kakao_acoount.getAsJsonObject("profile");


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.readTree(kakao_acoount.toString());
        KakaoMember kakaoMember = objectMapper.treeToValue(responseNode, KakaoMember.class);
        kakaoMember.setNickname(profile.get("nickname").getAsString());
        kakaoMember.setPassword(oAuthConfig.getKakaoPassword());

        log.info("카카오 인증 서버가 리턴한 사용자 정보: "+kakaoMember.toString());

        return kakaoMember.toMember();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }


}
