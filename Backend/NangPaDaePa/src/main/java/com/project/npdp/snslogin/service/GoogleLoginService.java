package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.snslogin.dto.GoogleTokenInfo;
import lombok.extern.slf4j.Slf4j;
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
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;

@Service
@Slf4j
public class GoogleLoginService {
    @Value("${spring.security.oauth2.default.password}")
    private String googlePassword;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.authorization-grant-type}")
    private String googleGrantType;

    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String googleAuthorizationUrl;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUrl;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoUrl;

    public String getGoogleAuthorizeUrl() throws UnsupportedEncodingException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(googleAuthorizationUrl)
                .queryParam("response_type", "code")
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", URLEncoder.encode(googleRedirectUri, "UTF-8"))
                .queryParam("scope", "email profile")
                .build();

        return uriComponents.toString();
    }

    public GoogleTokenInfo getGoogleToken(String code) {
//        log.info(String.format("google login code - %s", code));
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", googleGrantType);
        body.add("client_id", googleClientId);
        body.add("redirect_uri", googleRedirectUri);
        body.add("code", code);
        body.add("client_secret", googleClientSecret);

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                googleTokenUrl,
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
                GoogleTokenInfo googleTokenInfo = objectMapper.readValue(responseEntity.getBody(), GoogleTokenInfo.class);
                return googleTokenInfo;
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    public Member getMemberInfo(GoogleTokenInfo googleTokenInfo) {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer "+googleTokenInfo.getAccessToken());
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);

        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                googleUserInfoUrl+googleTokenInfo.getIdToken(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // 구글 인증 서버가 리턴한 사용자 정보
        String memberRawInfo = responseEntity.getBody();

        // 사용자 정보를 문자열 -> HashMap
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> tokenMap = gson.fromJson(memberRawInfo, type);
        log.info(String.format("memberRawInfo: %s", tokenMap));
        log.info(String.format("email: %s", tokenMap.get("email")));

        return null;
    }

}
