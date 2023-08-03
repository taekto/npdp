package com.project.npdp.member.service;

import com.project.npdp.member.dto.MemberJoinRequestDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private final MemberRepository memberRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    // 토큰 만료 시간 (임시: 1시간)
    private Long expiredMs = 1000 * 60 * 60l;

    // 회원 가입
    public void join(MemberJoinRequestDto memberJoinRequestDto){

        Member member = Member.builder()
                .email(memberJoinRequestDto.getEmail()).password(memberJoinRequestDto.getPassword()).nickname(memberJoinRequestDto.getNickname()).gender(memberJoinRequestDto.getGender()).birth(memberJoinRequestDto.getBirth()).build();

        // 중복 가입 방지 확인 (이메일)
        validateDuplicateJoin(memberJoinRequestDto);

        memberRepository.save(member);
    }

    // 중복 가입 방지
    private void validateDuplicateJoin(MemberJoinRequestDto memberJoinRequestDto) {
        List<Member> findMembers = memberRepository.findByEmail(memberJoinRequestDto.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public String login(String email, String password){
        // 인증과정 추가

        // 토큰 생성
        String tmp = JwtUtil.createJwt(email, secretKey, expiredMs);
        System.out.println(tmp);
        return tmp;
    }

    // 회원 단건 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        return member;
    }

}
