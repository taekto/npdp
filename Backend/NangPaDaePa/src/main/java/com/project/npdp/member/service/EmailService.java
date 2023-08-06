package com.project.npdp.member.service;

import com.project.npdp.member.entity.EmailMessage;
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


    public String sendAuthMail(EmailMessage emailMessage, String type){
        String authCode = createAuthCode();

        // JavaMail API를 사용해 이메일을 나타내는 객체
        // MimeMessage는 JavaMail API에서 이메일을 나타내는 클래스
        // 이 클래스를 사용해 이메일의 제목, 본문, 수신자, 발신자 등 설정 가능
        // JavaMailSender는 Spring에서 이메일을 발송하기 위한 인터페이스
        // createMimeMessage() 는 MimeMessage의 객체를 생성하는 메서드
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // 비밀번호 찾기(임시 비밀번호 발급)인 경우
        if(type.equals("password")) memberService.modifyPw(emailMessage.getTo(), authCode);

        try{
            // MimeMessageHelper는 MimeMessage를 보다 편리하게 사용하기위한 도우미 클래스
            // mimeMessage 객체와 인코딩 방식을 전달해 초기화 함
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            // setTo: 메일의 수신자 설정, getTo: 메일의 메시지를 전송할 수신자의 이메일 주소를 반환
            mimeMessageHelper.setTo(emailMessage.getTo());
            // setSubject: 메일의 제목을 설정, getTitle: 메일의 제목을 반환
            mimeMessageHelper.setSubject(emailMessage.getTitle());
            // setText: 메일의 본문을 설정, setContext: 메일의 본문을 동적으로 생성, true: HTML형식으로 설정된 본문
            mimeMessageHelper.setText(setContext(authCode, type), true);
            // 이메일을 실제로 전송
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
    // code: 템플릿에 삽입될 변수 값, type: 어떤 템플릿 사용할 지
    public String setContext(String code, String type){
        Context cxt = new Context();
        cxt.setVariable("code", code);
        // type에 해당하는 thymeleaf 템플릿을 context와 함께 처리해 동적으로 생성된 HTML 컨텐츠 반환
        // type은 사용할 템플릿의 파일명
        return springTemplateEngine.process(type, cxt);
    }
}
