package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.npdp.configuration.OAuthConfig;
import com.project.npdp.member.entity.Member;
import com.project.npdp.snslogin.dto.response.NaverMember;
import com.project.npdp.snslogin.dto.response.NaverToken;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class NaverLoginService implements OAuthProviderService<NaverToken>{

    private OAuthConfig oAuthConfig;

    @Autowired
    public NaverLoginService(OAuthConfig oAuthConfig) {
        this.oAuthConfig = oAuthConfig;
    }

    @Override
    public String getAuthorizeUrl() throws UnsupportedEncodingException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(oAuthConfig.getNaverAuthorizationUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", oAuthConfig.getNaverClientId())
                .queryParam("redirect_uri", URLEncoder.encode(oAuthConfig.getNaverRedirectUri(), "UTF-8"))
                .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                .build();

        return uriComponents.toString();
    }

    @Override
    public NaverToken getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Origin", "http://i9b202.p.ssafy.io/");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", oAuthConfig.getNaverGrantType());
        body.add("client_id", oAuthConfig.getNaverClientId());
        body.add("redirect_uri", oAuthConfig.getNaverRedirectUri());
        body.add("code", code);
        body.add("client_secret", oAuthConfig.getNaverClientSecret());

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getNaverTokenUrl(),
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                NaverToken naverToken = objectMapper.readValue(responseEntity.getBody(), NaverToken.class);
                return naverToken;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           log.error("Naver API returned an error: " + responseEntity.getBody());
        }

        return null;
    }

    @Override
    public Member getMemberInfo(NaverToken naverToken) {
        // RestTemplate을 이용하면 브라우저 없이 HTTP 요청을 처리할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", naverToken.getTokenType() + " " + naverToken.getAccessToken());
        headers.add("Origin", "http://i9b202.p.ssafy.io/");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기(body 정보는 생략 가능)
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        // HTTP 요청을 POST(GET) 방식으로 실행하기 -> 그러면 문자열로 응답이 들어온다.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getNaverUserInfoUrl(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        // 네이버 인증 서버가 리턴한 사용자 정보
        String memberInfo = responseEntity.getBody();

        try {
            // JSON 문자열을 UTF-8로 디코딩하여 사용자 정보를 추출
            String decodedInfo = URLDecoder.decode(memberInfo, StandardCharsets.UTF_8.toString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(decodedInfo, JsonObject.class);
            JsonObject response = jsonObject.getAsJsonObject("response");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseNode = objectMapper.readTree(response.toString());
            NaverMember naverMember = objectMapper.treeToValue(responseNode, NaverMember.class);
            naverMember.setPassword(oAuthConfig.getNaverPassword());

            log.info("네이버 인증 서버가 리턴한 사용자 정보: "+naverMember.toString());

            return naverMember.toMember();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
