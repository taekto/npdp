package com.project.npdp.member.controller;

import org.springframework.stereotype.Component;

@Component
public class MyAsyncComponent {

    public void asyncMethod(){
        // 비동기 작업을 수행하는 코드
        System.out.println("Async method started");
        try {
            Thread.sleep(2000); // 예시로 작업 시간을 지연시킴
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Async method completed");
    }

}
