package com.project.npdp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    // Jwt 생성
    public static String createJwt(String userNickname, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userNickname", userNickname);

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
