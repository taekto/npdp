package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.npdp.configuration.OAuthConfig;
import com.project.npdp.member.entity.Member;
import com.project.npdp.snslogin.dto.GoogleToken;
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
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;

@Service
@Slf4j
public class GoogleLoginService implements OAuthProviderService<GoogleToken>{

    private OAuthConfig oAuthConfig;

    @Autowired
    public GoogleLoginService(OAuthConfig oAuthConfig) {
        this.oAuthConfig = oAuthConfig;
    }

    @Override
    public String getAuthorizeUrl() throws UnsupportedEncodingException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(oAuthConfig.getGoogleAuthorizationUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", oAuthConfig.getGoogleClientId())
                .queryParam("redirect_uri", URLEncoder.encode(oAuthConfig.getGoogleRedirectUri(), "UTF-8"))
                .queryParam("scope", "email profile")
                .build();

        return uriComponents.toString();
    }

    @Override
    public GoogleToken getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", oAuthConfig.getGoogleGrantType());
        body.add("client_id", oAuthConfig.getGoogleClientId());
        body.add("redirect_uri", oAuthConfig.getGoogleRedirectUri());
        body.add("code", code);
        body.add("client_secret", oAuthConfig.getGoogleClientSecret());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getGoogleTokenUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                GoogleToken googleToken = objectMapper.readValue(responseEntity.getBody(), GoogleToken.class);
                return googleToken;
            }catch(IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Google API returned an error: " + responseEntity.getBody());
        }

        return null;
    }

    public Member getMemberInfo(GoogleToken googleToken) {
        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 생성
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer "+googleToken.getAccessToken());

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getGoogleUserInfoUrl()+googleToken.getIdToken(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // 구글 인증 서버가 리턴한 사용자 정보
        String memberInfo = responseEntity.getBody();

        // 사용자 정보를 문자열 -> HashMap
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> tokenMap = gson.fromJson(memberInfo, type);
//        log.info(String.format("memberRawInfo: %s", tokenMap));
//        log.info(String.format("email: %s", tokenMap.get("email")));

        return null;
    }

}
