package com.project.npdp.member.entity;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class EmailMessage {

    // 수신자
    private String to;

    // 메일 제목
    private String title;

    // 메일 내용
    private String content;

    @Builder
    public EmailMessage(String to, String title){
        this.to = to;
        this.title = title;
    }

}
