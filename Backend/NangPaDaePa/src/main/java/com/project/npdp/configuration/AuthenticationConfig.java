package com.project.npdp.configuration;

import com.project.npdp.member.service.MemberService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity      // Web 보안 활성화
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final MemberService memberService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Form Based Authentication 사용 안함
                .httpBasic().disable()
                // rest api에서 권한 필요한 요청위해 인증 정보 포함시켜 보내야 함
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                // 해당 api 모든 요청 허가
//                .antMatchers("/api/members/localLogin", "/api/members/join", "/api/oauth/**").permitAll()
                .antMatchers("/api/**").permitAll()
                // 해당 api 모든 요청 불허
//                .antMatchers(HttpMethod.POST).authenticated()
                .and()
                // JWT 사용으로 session 사용 안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    // UsernamePasswordAuthenticationFilter 가기 전에 JwtTokenFilter 주입
                    // username과 pw로 인증 확인 전에 토큰 정보를 확인함으로써 인증 절차 걸침
                    .addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
}
