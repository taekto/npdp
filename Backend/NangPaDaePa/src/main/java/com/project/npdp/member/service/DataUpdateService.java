package com.project.npdp.member.service;

import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.member.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class DataUpdateService {

    private final DataRepository dataRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    // Django에서 비동기로 유사도 Get
    @Async
    public CompletableFuture<Void> fetchDataFromDjangoApi() {

        String api = "http://i9b202.p.ssafy.io/data/recommend/users";

        // 업데이트시 기존 데이터 모두 삭제
//        dataRepository.deleteAll();

        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Void> responseEntity = restTemplate.getForEntity(api, Void.class);
        restTemplate.getForEntity(api, Void.class);

        return CompletableFuture.completedFuture(null); // 상태 코드 반환
    }



//    // 유사도 저장
//    @Transactional
//    public void updateMemberRecommend(List<MemberRecommend> memberRecommends){
//        for(MemberRecommend result : memberRecommends){
//            dataRepository.save(result);
//        }
//    }

}
