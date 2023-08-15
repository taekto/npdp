package com.project.npdp.member.controller;

import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.member.service.DataUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class DataUpdateScheduler {

    private final DataUpdateService dataUpdateService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)     // 24시간마다 업뎃
    public void updateDataAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<List<MemberRecommend>> result = dataUpdateService.fetchDataFromDjangoApi();
        List<MemberRecommend> memberRecommends = result.get();

//        dataUpdateService.updateMemberRecommend(memberRecommends);
    }

}
