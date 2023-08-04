package com.project.npdp.member.service;

import com.project.npdp.member.dto.request.*;
import com.project.npdp.member.dto.response.MemberDetailResponseDto;
import com.project.npdp.member.dto.response.MemberLoginResponseDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.utils.JwtUtil;
import com.project.npdp.utils.SHA256Util;
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

        // 비밀번호 해시 암호화
        String hashedPw = SHA256Util.getSHA256(memberJoinRequestDto.getPassword());

        System.out.println("hashedPw" + hashedPw);

        Member member = Member.builder()
                .email(memberJoinRequestDto.getEmail())
                .password(hashedPw)
                .nickname(memberJoinRequestDto.getNickname())
                .gender(memberJoinRequestDto.getGender())
                .birth(memberJoinRequestDto.getBirth())
                .build();
        // 중복 가입 방지 확인 (이메일)
        validateDuplicateJoin(memberJoinRequestDto);
        memberRepository.save(member);
    }

    // 중복 가입 방지
    private void validateDuplicateJoin(MemberJoinRequestDto memberJoinRequestDto) {
        Member findMembers = memberRepository.findByEmail(memberJoinRequestDto.getEmail());
        if(findMembers != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto){
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        // 이메일 및 비번 인증과정
        Member findMemberByEmail = memberRepository.findByEmail(email);
        if(findMemberByEmail == null || !findMemberByEmail.authenticate(email, password)){
            return null;
        }
        // 토큰 생성
        String jwt = JwtUtil.createJwt(email, secretKey, expiredMs);
        // 토큰과 닉네임 반환
        MemberLoginResponseDto result = MemberLoginResponseDto.builder()
                .id(findMemberByEmail.getId())
                .nickname(findMemberByEmail.getNickname())
                .accessToken(jwt)
                .build();

        return result;
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
    
    // 닉네임 변경
    public void modifyNickname(MemberNicknameRequestDto memberNicknameRequestDto){
        Long id = memberNicknameRequestDto.getId();
        String nickname = memberNicknameRequestDto.getNickname();

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.modifyNickname(nickname);
            memberRepository.save(member);
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }
    
    // 성별 변경
    public void modifyGender(MemberGenderRequestDto memberGenderRequestDto){
        Long id = memberGenderRequestDto.getId();
        String gender = memberGenderRequestDto.getGender();

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.modifyGender(gender);
            memberRepository.save(member);
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }
    
    // 생일 변경
    public void modifyBirth(MemberBirthRequestDto memberBirthRequestDto){
        Long id = memberBirthRequestDto.getId();
        String birth = memberBirthRequestDto.getBirth();

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.modifyBirth(birth);
            memberRepository.save(member);
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }
}
