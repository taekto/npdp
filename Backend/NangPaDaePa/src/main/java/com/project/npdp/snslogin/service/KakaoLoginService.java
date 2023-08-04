package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class KakaoLoginService {

    @Value("${spring.security.oauth2.default.password}")
    private String kakaoPassword;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    public String getAccessToken(String code) {
        String kakaoTokenUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "application/json");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", kakaoRedirectUri);
        body.add("code", code);
        body.add("client_secret", kakaoClientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(kakaoTokenUrl,HttpMethod.POST, requestEntity, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
                return jsonNode.get("access_token").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Member getMemberInfo(String accessToken){
        // HttpHeader 생성
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + accessToken);
        header.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(header);

        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 카카오 인증 서버가 리턴한 사용자 정보
        String userInfo = responseEntity.getBody();

        // JSON 데이터에서 추출한 정보를 바탕으로 User 객체 설정
        Gson gsonObj = new Gson();
        Map<?, ?> data = gsonObj.fromJson(userInfo, Map.class);
		Double id = (Double) (data.get("id"));

        String email = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("email");
        String nickname = (String) ((Map<?, ?>) (data.get("properties"))).get("nickname");
        String gender = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("gender");
        String birthyear = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("birthyear");
        String birthday = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("birthday");
        String birth = String.format("%s%s", birthyear,birthday).replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1.$2.$3");

//        log.info(String.format("nickname: %s", nickname));
//        log.info(String.format("email: %s", email));
//        log.info(String.format("gender: %s", gender));
//        log.info(String.format("birthyear: %s", birthyear));
//        log.info(String.format("birthday: %s", birthday));

        Member member;
        if(gender.equals("male")){
            member = Member.builder().email(email).nickname(nickname).password(kakaoPassword).oauth(OAuthType.KAKAO).birth(birth).gender(Gender.MALE).build();
        }else{
            member = Member.builder().email(email).nickname(nickname).password(kakaoPassword).oauth(OAuthType.KAKAO).birth(birth).gender(Gender.FEMALE).build();
        }
        return member;
    }
}
