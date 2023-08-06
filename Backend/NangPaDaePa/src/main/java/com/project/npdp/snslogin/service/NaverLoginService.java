package com.project.npdp.snslogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.npdp.configuration.OAuthConfig;
import com.project.npdp.member.entity.Member;
import com.project.npdp.snslogin.dto.NaverToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
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
        // Prepare headers for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Prepare request body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", oAuthConfig.getNaverGrantType());
        body.add("client_id", oAuthConfig.getNaverClientId());
        body.add("redirect_uri", oAuthConfig.getNaverRedirectUri());
        body.add("code", code);
        body.add("client_secret", oAuthConfig.getNaverClientSecret());

        // Create an HTTP entity with headers and body
        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(body, headers);

        try {
            // Make the POST request to obtain the access token
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    oAuthConfig.getNaverTokenUrl(),
                    HttpMethod.POST,
                    accessTokenRequest,
                    String.class
            );

            // Check the response status
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // Parse the JSON response to extract token information
                    JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
//                    NaverTokenInfo naverTokenInfo = new NaverTokenInfo(
//                            jsonNode.get("access_token").asText(),
//                            jsonNode.get("refresh_token").asText(),
//                            jsonNode.get("token_type").asText(),
//                            jsonNode.get("expires_in").asText()
//                    );
                    NaverToken naverToken = objectMapper.readValue(responseEntity.getBody(), NaverToken.class);
                    return naverToken;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle error response
                System.out.println("Naver API returned an error: " + responseEntity.getBody());
                // You can throw a custom exception or handle the error in another way
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            // You can throw a custom exception or handle the error in another way
        }

        // Return null if an error occurs
        return null;
    }

    @Override
    public Member getMemberInfo(NaverToken naverToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", naverToken.getTokenType() + " " + naverToken.getAccessToken());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                oAuthConfig.getNaverUserInfoUrl(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        String memberRawInfo = responseEntity.getBody();

        try {
            // JSON 문자열을 UTF-8로 디코딩하여 사용자 정보를 추출
            String decodedInfo = URLDecoder.decode(memberRawInfo, StandardCharsets.UTF_8.toString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(decodedInfo, JsonObject.class);
            JsonObject response = jsonObject.getAsJsonObject("response");

            log.info("네이버 인증 서버가 리턴한 사용자 정보: " + memberRawInfo);
            log.info("사용자 정보만 뽑아낸 것: " + response.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
