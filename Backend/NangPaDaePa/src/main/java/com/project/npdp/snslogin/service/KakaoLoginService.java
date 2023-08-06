package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.npdp.configuration.OAuthConfig;
import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.snslogin.dto.KakaoToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Map;

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

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", oAuthConfig.getKakaoGrantType());
        body.add("client_id", oAuthConfig.getKakaoClientId());
        body.add("redirect_uri", oAuthConfig.getKakaoRedirectUri());
        body.add("code", code);
        body.add("client_secret", oAuthConfig.getKakaoClientSecret());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(oAuthConfig.getKakaoTokenUrl(),HttpMethod.POST, requestEntity, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                KakaoToken kakaoToken = objectMapper.readValue(responseEntity.getBody(), KakaoToken.class);
                return kakaoToken;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Member getMemberInfo(KakaoToken kakaoToken){
        // HttpHeader 생성
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + kakaoToken.getAccessToken());
        header.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(header);

        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getKakaoUserInfoUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 카카오 인증 서버가 리턴한 사용자 정보
        String userInfo = responseEntity.getBody();

        // JSON 데이터에서 추출한 정보를 바탕으로 Member 객체 설정
        Gson gsonObj = new Gson();
        Map<?, ?> data = gsonObj.fromJson(userInfo, Map.class);
		Double id = (Double) (data.get("id"));

        String email = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("email");
        String nickname = (String) ((Map<?, ?>) (data.get("properties"))).get("nickname");
        String gender = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("gender");
        String birthyear = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("birthyear");
        String birthday = (String) ((Map<?, ?>) (data.get("kakao_account"))).get("birthday");
        String birth = String.format("%s%s", birthyear,birthday).replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1.$2.$3");

        Member member;
        if(gender.equals("male")){
            member = Member.builder().email(email).nickname(nickname).password(oAuthConfig.getKakaoPassword()).oauth(OAuthType.KAKAO).birth(birth).gender(Gender.MALE).build();
        }else{
            member = Member.builder().email(email).nickname(nickname).password(oAuthConfig.getKakaoPassword()).oauth(OAuthType.KAKAO).birth(birth).gender(Gender.FEMALE).build();
        }
        return member;
    }


}
