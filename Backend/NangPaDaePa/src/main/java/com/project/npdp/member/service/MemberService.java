package com.project.npdp.member.service;

import com.project.npdp.member.dto.request.MemberJoinRequestDto;
import com.project.npdp.member.dto.response.MemberDetailResponseDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.utils.JwtUtil;
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


    public String snsLogin(Member member){
//         이메일 중복 여부 확인
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
//        1. 이미 등록된 이메일인 경우
        if(!findMembers.isEmpty()){
//            -1. 같은 SNS 로그인인 경우: 해당 sns 로그인으로 바로 로그인
            if(findMembers.get(0).getOauth().equals(member.getOauth())){
                return this.login(member.getEmail(), member.getPassword());
//            -2. 로컬 로그인이거나 다른 SNS 로그인인 경우: ERROR 발생
            }else{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
//        2. 없는 이메일인 경우
        }else{
            return "new";
        }
    }

    // 회원id로 상세정보 조회
    @Transactional(readOnly = true)
    public MemberDetailResponseDto findMemberById(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 상세정보 조회 불가"));

        MemberDetailResponseDto result = MemberDetailResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .gender(member.getGender())
                .birth(member.getBirth())
                .build();

        return result;
    }
}
