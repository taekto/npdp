package com.project.npdp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    // 이메일 꺼내기
    public static String getUserEmail(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userEmail", String.class);
    }

    // 토큰 만료 확인
    public static boolean isExpired(String token, String secretKey){
        // 토큰의 만료기간이 현재 시각보다 이전이면 true 반환
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    // Jwt 생성
    public static String createJwt(String userEmail, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);

        return Jwts.builder()
                .setClaims(claims)
                // 오늘 날짜
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 만료 기한
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                // 토큰에 서명
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 연관 배열 생성
                .compact();
    }
}
