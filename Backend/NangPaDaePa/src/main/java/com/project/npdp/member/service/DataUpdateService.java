package com.project.npdp.member.service;

import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.member.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class DataUpdateService {

    private final DataRepository dataRepository;
//    private RestTemplate restTemplate;

    // Django에서 비동기로 유사도 Get
    @Async
    public CompletableFuture<List<MemberRecommend>> fetchDataFromDjangoApi(){

        String api = "https://i9b202.p.ssafy.io/data/recommend/user";

        // 업데이트시 기존 데이터 모두 삭제
        dataRepository.deleteAll();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MemberRecommend[]> responseEntity = restTemplate.getForEntity(api, MemberRecommend[].class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return CompletableFuture.completedFuture(Arrays.asList(responseEntity.getBody()));
        }else{
            throw new RuntimeException("유사도 데이터 업데이트에 실패했습니다.");
        }

    }


//    // 유사도 저장
//    @Transactional
//    public void updateMemberRecommend(List<MemberRecommend> memberRecommends){
//        for(MemberRecommend result : memberRecommends){
//            dataRepository.save(result);
//        }
//    }

}
