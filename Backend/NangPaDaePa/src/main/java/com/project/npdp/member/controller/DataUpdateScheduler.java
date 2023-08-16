package com.project.npdp.member.controller;

import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.member.service.DataUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
//@EnableAsync
//@EnableScheduling
@RequiredArgsConstructor
public class DataUpdateScheduler {

    private final DataUpdateService dataUpdateService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)     // 24시간마다 업뎃
    public void scheduleAsyncMethod() {
        CompletableFuture<Void> future = dataUpdateService.fetchDataFromDjangoApi();
        System.out.println("Scheduled task completed");
//        try {
//            future.get(); // 비동기 작업이 완료될 때까지 대기
//            System.out.println("Scheduled task completed");
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

}
