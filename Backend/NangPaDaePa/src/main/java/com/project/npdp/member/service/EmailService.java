package com.project.npdp.member.service;

import com.project.npdp.member.entity.EmailMessage;
import com.project.npdp.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final MemberService memberService;
    private final RedisUtil redisUtil;


    public String sendAuthMail(EmailMessage emailMessage, String type){
        String authCode = createAuthCode();

        // redis에 이메일과 인증코드 저장 (임시 5분)
        // 만약 이전에 저장된 값 있으면 삭제
        if(redisUtil.existData(emailMessage.getTo())){
            redisUtil.deleteData(emailMessage.getTo());
        }
        redisUtil.setDataExpire(emailMessage.getTo(), authCode, 60*5L);

        // JavaMail API를 사용해 이메일의 속성 결정
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // 비밀번호 찾기(임시 비밀번호 발급)인 경우
        if(type.equals("password")) memberService.modifyPw(emailMessage.getTo(), authCode);

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getTitle());
            mimeMessageHelper.setText(setContext(authCode, type), true);
            // 이메일 전송
            javaMailSender.send(mimeMessage);

            // 이메일에 포함될 인증 코드 반환 -> 이메일 전송 후 다른 작업에 사용 가능
            return authCode;

        } catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }

    // 인증번호 및 임시 비밀번호 생성
    public String createAuthCode(){
        Random random = new Random();
        StringBuffer code = new StringBuffer();

        for(int i=0; i<6; i++){
            int idx = random.nextInt(4);
            switch (idx){
                case 0: code.append((char)((int) random.nextInt(26) + 97)); break;  // 소문자
                case 1: code.append((char)((int) random.nextInt(26) + 65)); break;  // 대문자
                default:code.append(random.nextInt(9));
            }
        }
        return code.toString();
    }

    // thymeleaf(템플릿 엔진 사용해 동적으로 HTML 컨텐츠 생성) 통한 html 적용
    public String setContext(String code, String type){
        Context cxt = new Context();
        cxt.setVariable("code", code);
        // email 타입의 thymeleaf 템플릿을 동적으로 생성된 HTML 컨텐츠 반환
        return springTemplateEngine.process(type, cxt);
    }
}
