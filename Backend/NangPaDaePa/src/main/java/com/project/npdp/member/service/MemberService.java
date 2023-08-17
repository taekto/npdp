package com.project.npdp.member.service;

import com.project.npdp.domain.Allergy;
import com.project.npdp.domain.Utensil;
import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.member.dto.request.*;
import com.project.npdp.member.dto.response.*;
import com.project.npdp.member.entity.*;
import com.project.npdp.member.repository.*;
import com.project.npdp.snslogin.dto.response.SnsLoginResponseDto;
import com.project.npdp.utils.JwtUtil;
import com.project.npdp.utils.SHA256Util;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
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

    // 프론트에서 넘어오는 성별
    private final String male = "male";
    private final String female = "female";

    // 회원 가입
    public void join(MemberJoinRequestDto memberJoinRequestDto){

        // 비밀번호 해시 암호화
        String hashedPw = SHA256Util.getSHA256(memberJoinRequestDto.getPassword());

        // 성별 처리
        String tmpGender = memberJoinRequestDto.getGender();
        Gender gender = this.stringToGender(tmpGender);

        System.out.println("hashedPw" + hashedPw);

        Member member = Member.builder()
                .email(memberJoinRequestDto.getEmail())
                .nickname(memberJoinRequestDto.getNickname())
                .password(hashedPw)
                .oauth(OAuthType.LOCAL)
                .role(Role.MEMBER)
                .birth(memberJoinRequestDto.getBirth())
                .gender(gender)
                .build();
        // 중복 가입 방지 확인 (이메일)
        validateDuplicateJoin(memberJoinRequestDto);
        memberRepository.save(member);
    }

    // 회원 가입
    public void snsJoin(Member newMember){
        log.info("snsJoin입니다");
        log.info("새롭게 들어오는 member의 oauth: "+newMember.getOauth());

        // 비밀번호 해시 암호화
        String hashedPw = SHA256Util.getSHA256(newMember.getPassword());

        Member member = Member.builder()
                .email(newMember.getEmail())
                .nickname(newMember.getNickname())
                .password(hashedPw)
                .oauth(newMember.getOauth())
                .role(newMember.getRole())
                .birth(newMember.getBirth())
                .gender(newMember.getGender())
                .build();

        // 중복 가입 방지 확인 (이메일)
        validateDuplicateJoin(MemberJoinRequestDto.builder().email(member.getEmail()).build());
        memberRepository.save(member);
    }

    // 중복 가입 방지
    private void validateDuplicateJoin(MemberJoinRequestDto memberJoinRequestDto) {
        Member findMembers = memberRepository.findByEmail(memberJoinRequestDto.getEmail());
        if(findMembers != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 로그인
    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto){
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        // 이메일 및 비번 인증과정
        Member member = memberRepository.findByEmail(email);
        if(member == null || !member.authenticate(email, password)){
            return null;
        }
        // 토큰 생성
        String jwt = JwtUtil.createJwt(email, secretKey, expiredMs);
        // 토큰과 닉네임 반환
        MemberLoginResponseDto result = MemberLoginResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .accessToken(jwt)
                .build();

        return result;
    }

    // 비밀번호 확인
    public void checkPw(MemberCheckPwRequestDto memberCheckPwRequestDto) {
        String email = memberCheckPwRequestDto.getEmail();
        String password = SHA256Util.getSHA256(memberCheckPwRequestDto.getPassword());

        Member member = memberRepository.findByEmail(email);

//        log.info("DB에 저장된 비번: " + member.getPassword());
//        log.info("현재 들어온 비번 변환: " + password);

        if (member == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }else if(!member.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }

    // sns 로그인
    public SnsLoginResponseDto snsLogin(Member member){
//         이메일 중복 여부 확인
        Member findMembers = memberRepository.findByEmail(member.getEmail());
//        1. 이미 등록된 이메일인 경우
        if(findMembers != null){
//            -1. 같은 SNS 로그인인 경우: 해당 sns 로그인으로 바로 로그인
            if(findMembers.getOauth().equals(member.getOauth())){
                MemberLoginResponseDto memberLoginResponseDto = this.login(MemberLoginRequestDto.builder().email(member.getEmail()).password(member.getPassword()).build());
                return SnsLoginResponseDto.builder().memberLoginResponseDto(memberLoginResponseDto).httpStatus(HttpStatus.OK).build();
//            -2. 로컬 로그인이거나 다른 SNS 로그인인 경우: ERROR 발생
            }else{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
//        2. 없는 이메일인 경우
        }else{
            this.snsJoin(member);
            MemberLoginResponseDto memberLoginResponseDto = this.login(MemberLoginRequestDto.builder().email(member.getEmail()).password(member.getPassword()).build());
            return SnsLoginResponseDto.builder().memberLoginResponseDto(memberLoginResponseDto).httpStatus(HttpStatus.CREATED).build();
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

    // 비밀번호 변경 (발급된 임시 비밀번호 또는 새로운 비밀번호)
    public void modifyPw(String email, String newPw){
        Member member = memberRepository.findByEmail(email);
        if(member != null){
//            log.info("newPw: " + newPw);
            member.modifyPw(SHA256Util.getSHA256(newPw));
            memberRepository.save(member);
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
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

//        log.info("gender: " + gender);

        Gender newGender = null;
        if(gender.equals(male)){
            newGender = Gender.MALE;
        }else if(gender.equals(female)){
            newGender = Gender.FEMALE;
        }

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.modifyGender(newGender);
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

    public void modifyAll(MemberModifyAllRequestDto memberModifyAllRequestDto){
        Long id = memberModifyAllRequestDto.getMemberId();
        String password = memberModifyAllRequestDto.getPassword();
        String nickname = memberModifyAllRequestDto.getNickname();
        String birth = memberModifyAllRequestDto.getBirth();
        Gender gender = memberModifyAllRequestDto.getGender();

        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            // null 값이 아닌 값들만 수정 (비밀번호 제외했음)
            if(password!=null) member.modifyPw(password);
            if(nickname!=null) member.modifyNickname(nickname);
            if(birth!=null) member.modifyBirth(birth);
            if(gender!=null && gender.equals("여성")) member.modifyGender(Gender.FEMALE);
            else if (gender!=null && gender.equals("남성")) member.modifyGender(Gender.MALE);

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
        memberAllergyRepository.deleteMemberAllergy(memberId);

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

    // 회원 알러지 정보 조회
    public List<MemberAllergyResponseDto> getMemberAllergy(Long memberId){
        List<MemberAllergy> memberAllergies = memberAllergyRepository.selectMemberAllergy(memberId);

        List<MemberAllergyResponseDto> memberAllergyResponseDto = new ArrayList<>();
        for(MemberAllergy memberAllergy : memberAllergies){
            MemberAllergyResponseDto memberAllergyResponse = MemberAllergyResponseDto.builder()
                    .allergyId(memberAllergy.getAllergy().getId())
                    .allergyName(memberAllergy.getAllergy().getName())
                    .build();

            memberAllergyResponseDto.add(memberAllergyResponse);
        }
        return memberAllergyResponseDto;
    }
    
    // 회원 비선호 재료 입력
    public void insertDislikeIngredient(MemberDislikeRequestDto memberDislikeRequestDto){
        Long memberId = memberDislikeRequestDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);

        // 기존회원의 비선호 재료 정보 삭제
        memberDislikeRepository.deleteDislikeIngredient(memberId);

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

    // 비선호 재료 정보 조회
    public List<MemberDislikeIngredientResponseDto> getDislikeIngredient(Long memberId){
        List<MemberDislikeIngredient> memberDislikeIngredients = memberDislikeRepository.selectDislikeIngredient(memberId);

        List<MemberDislikeIngredientResponseDto> memberDislikeIngredientResponseDtos = new ArrayList<>();
        for(MemberDislikeIngredient memberDislike : memberDislikeIngredients){
            MemberDislikeIngredientResponseDto memberDislikeIngredientResponseDto = MemberDislikeIngredientResponseDto.builder()
                    .ingredientId(memberDislike.getIngredient().getId())
                    .ingredientName(memberDislike.getIngredient().getKor())
                    .build();
            memberDislikeIngredientResponseDtos.add(memberDislikeIngredientResponseDto);
        }
        return memberDislikeIngredientResponseDtos;
    }
    
    // 회원 조리도구 입력
    public void insertMemberUtensil(MemberUtensilRequestDto memberUtensilRequestDto){
        Long memberId = memberUtensilRequestDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);

        // 기존에 저장된 조리도구 정보 삭제
        memberUtensilRepository.deleteMemberUtensil(memberId);

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

    // 회원 조리도구 정보 조회
    public List<MemberUtensilResponseDto> getMemberUtensil(Long memberId){
        List<MemberUtensil> memberUtensils = memberUtensilRepository.selectMemberUtensil(memberId);

        List<MemberUtensilResponseDto> memberUtensilResponseDtos = new ArrayList<>();
        for(MemberUtensil memberUtensil : memberUtensils){
            MemberUtensilResponseDto memberUtensilResponseDto = MemberUtensilResponseDto.builder()
                    .utensilId(memberUtensil.getUtensil().getId())
                    .utensilName(memberUtensil.getUtensil().getName())
                    .build();

            memberUtensilResponseDtos.add(memberUtensilResponseDto);
        }
        return memberUtensilResponseDtos;
    }

    // 회원 탈퇴
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElse(null);

        if(member != null){
            // 탈퇴일 설정
            LocalDateTime now = LocalDateTime.now();
            // LocalDateTime -> 문자열
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            // 문자열 -> LocalDateTime
            LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);

            member.modifyQuit(parsedDateTime);
            memberRepository.save(member);
            // 회원 정보 삭제
//            memberRepository.delete(member);
            // 회원 알러지 정보 삭제
//            memberAllergyRepository.deleteAllByMember(member);
//            // 회원 조리도구 정보 삭제
//            memberUtensilRepository.deleteAllByMember(member);
//            // 회원 비선호재료 정보 삭제
//            memberDislikeRepository.deleteAllByMember(member);
            // 회원
        }else{
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }

    public Gender stringToGender(String raw){
        Gender gender = null;
        if(raw.equals(male)){
            gender = Gender.MALE;
        }else if(raw.equals(female)){
            gender = Gender.FEMALE;
        }
        return gender;
    }

    public String genderToString(Gender gender){
        if(gender == Gender.MALE){
            return male;
        }else if(gender == Gender.FEMALE){
            return female;
        }
        return null;
    }
}
