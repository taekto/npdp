package com.project.npdp.configuration;

import com.project.npdp.member.service.MemberService;
import com.project.npdp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 매번 토큰 인증 필요
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final MemberService memberService;
    private final String secretKey;

    // jwt 인증 관문
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        // 토큰 안보내면 filterChain으로 이동하게 하고 리턴
        // 토큰의 맨 앞은 Bearer로 시작 약속
        log.error("authorization 이 없습니다.");
        if(authorization == null || !authorization.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer제외하고 토큰만 꺼내기
        String token = authorization.split(" ")[1];
        System.out.println("splitToken: " + token);

        // Token Expired되었는지 여부
        if(JwtUtil.isExpired(token, secretKey)){
            log.error("token이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 userEmail 꺼내기
        String userEmail = JwtUtil.getUserEmail(token, secretKey);
        log.info("userEmail:{}", userEmail);

        // 권한 부여 (DB role)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userEmail, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 인증 도장
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
