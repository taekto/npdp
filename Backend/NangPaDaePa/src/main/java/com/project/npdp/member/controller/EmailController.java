package com.project.npdp.member.controller;

import com.project.npdp.member.dto.request.EmailAuthRequestDto;
import com.project.npdp.member.dto.request.EmailVerityRequestDto;
import com.project.npdp.member.dto.response.EmailAuthResponseDto;
import com.project.npdp.member.entity.EmailMessage;
import com.project.npdp.member.service.EmailService;
import com.project.npdp.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final RedisUtil redisUtil;

    // 회원가입 이메일 인증
    @PostMapping("/joinAuth")
    public ResponseEntity<?> sendJoinAuth(@RequestBody EmailAuthRequestDto emailAuthRequestDto){
        log.info("emailAuthRequestDto = {}", emailAuthRequestDto);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailAuthRequestDto.getEmail())
                .title("[냉파대파] 이메일 인증을 위한 인증 코드")
                .build();

        String code = emailService.sendAuthMail(emailMessage, "email");

        EmailAuthResponseDto emailAuthResponseDto = new EmailAuthResponseDto(code);

        // body로 인증코드 반환
        return ResponseEntity.ok(emailAuthResponseDto);
    }

    // 비밀번호 찾기 (임시 비밀번호 발급)
    @PostMapping("/password")
    public ResponseEntity<?> sendPwAuth(@RequestBody EmailAuthRequestDto emailAuthRequestDto){
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailAuthRequestDto.getEmail())
                .title("[냉파대파] 임시 비밀번호 발급")
                .build();

        emailService.sendAuthMail(emailMessage, "password");

        return ResponseEntity.ok().build();
    }

    // 이메일 인증코드 확인
    @PostMapping("/verifyAuth")
    public int verifyAuthCode(@RequestBody EmailVerityRequestDto emailVerityRequestDto){
        String redisAuthCode = redisUtil.getDate(emailVerityRequestDto.getEmail());
        // 인증번호가 일치하면
        if(redisAuthCode.equals(emailVerityRequestDto.getCode())){
            return 1;
        }else if(redisAuthCode != null && !redisAuthCode.equals(emailVerityRequestDto.getCode())){
            return 2;
        }else
            return 3;
    }
}
