package com.project.npdp.member.service;

import com.project.npdp.domain.Allergy;
import com.project.npdp.domain.Utensil;
import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.member.dto.request.*;
import com.project.npdp.member.dto.response.MemberDetailResponseDto;
import com.project.npdp.member.dto.response.MemberLoginResponseDto;
import com.project.npdp.member.entity.*;
import com.project.npdp.member.repository.*;
import com.project.npdp.utils.JwtUtil;
import com.project.npdp.utils.SHA256Util;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAllergyRepository memberAllergyRepository;
    private final MemberDislikeRepository memberDislikeRepository;
    private final MemberUtensilRepository memberUtensilRepository;
    private final IngredientRepository ingredientRepository;
    private final AllergyRepository allergyRepository;
    private final UtensilRepository utensilRepository;

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
        Long id = memberNicknameRequestDto.getMemberId();
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
        Long id = memberGenderRequestDto.getMemberId();
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
        Long id = memberBirthRequestDto.getMemberId();
        String birth = memberBirthRequestDto.getBirth();

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.modifyBirth(birth);
            memberRepository.save(member);
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }
    
    // 회원 알러지 입력
    public void insertMemberAllergy(MemberAllergyRequestDto memberAllergyRequestDto){
        Long memberId = memberAllergyRequestDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);

        // 기존에 저장된 알러지 정보 삭제
        MemberAllergy allergyMember = memberAllergyRepository.findById(memberId).orElse(null);
        if(allergyMember != null){
            memberAllergyRepository.deleteAllByMember(member);
        }

        // 새로운 알러지 정보 저장
        List<MemberAllergy> memberAllergyList = new ArrayList<>();
        for(Long allergyId : memberAllergyRequestDto.getAllergyId()){
            Allergy allergy = allergyRepository.findById(allergyId).orElse(null);
            if(allergy != null){
                MemberAllergy memberAllergy;
                memberAllergy = new MemberAllergy(member, allergy);
                memberAllergyList.add(memberAllergy);
            }else{
                throw new IllegalArgumentException("해당 알러지를 찾을 수 없습니다");
            }
            // 기존 저장된 알러지 모두 삭제한 후 새로운 알러지 리스트로 대체
            memberAllergyRepository.saveAll(memberAllergyList);
        }
    }
    
    // 회원 비선호 재료 입력
    public void insertDislikeIngredient(MemberDislikeRequestDto memberDislikeRequestDto){
        Long memberId = memberDislikeRequestDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);

        // 기존회원의 비선호 재료 정보 삭제
        MemberDislikeIngredient dislikeMember = memberDislikeRepository.findById(memberId).orElse(null);
        if(dislikeMember != null){
            memberDislikeRepository.deleteAllByMember(member);
        }

        // 새로운 비선호 재료 정보 저장
        List<MemberDislikeIngredient> memberDislikeIngredientList = new ArrayList<>();
        for(Long ingredientId : memberDislikeRequestDto.getIngredientId()){
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
            if(ingredient != null){
                MemberDislikeIngredient memberDislikeIngredient;
                memberDislikeIngredient = new MemberDislikeIngredient(member, ingredient);
                memberDislikeIngredientList.add(memberDislikeIngredient);
            }else{
                throw new IllegalArgumentException("해당 재료를 찾을 수 없습니다");
            }
            // 기존 저장된 비선호 재료 모두 삭제한 후 새로운 재료 리스트로 대체
            memberDislikeRepository.saveAll(memberDislikeIngredientList);
        }
    }
    
    // 회원 조리도구 입력
    public void insertMemberUtensil(MemberUtensilRequestDto memberUtensilRequestDto){
        Long memberId = memberUtensilRequestDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);

        // 기존에 저장된 조리도구 정보 삭제
        MemberUtensil utensilMember = memberUtensilRepository.findById(memberId).orElse(null);
        if(utensilMember != null){
            memberUtensilRepository.deleteAllByMember(member);
        }

        // 새로운 조리도구 정보 저장
        List<MemberUtensil> memberUtensilList = new ArrayList<>();
        for(Long utensilId : memberUtensilRequestDto.getUtensilId()){
            Utensil utensil = utensilRepository.findById(utensilId).orElse(null);
            if(utensil != null){
                MemberUtensil memberUtensil;
                memberUtensil = new MemberUtensil(member, utensil);
                memberUtensilList.add(memberUtensil);
            }else{
                throw new IllegalArgumentException("해당 조리도구를 찾을 수 없습니다");
            }
            // 기존 저장된 조리도구 모두 삭제한 후 새로운 조리도구 리스트로 대체
            memberUtensilRepository.saveAll(memberUtensilList);
        }
    }
}
