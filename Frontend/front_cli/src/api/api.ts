import member from "../store/modules/member";

const HOST = 'http://i9b202.p.ssafy.io:8080/api/';

const MEMBER = 'members/';
const ALLERGY = 'allergy/';
const RECIPES = 'recipes/';
const DISLIKE = 'dislikeingredient/'
const UTENSIL = 'utensil/'
const CALENDAR = 'calendar/'


export default {
  // member_id or member_id
  member: {
    // 회원 가입
    signup:() => HOST + MEMBER + 'join',

    // 회원 로컬 로그인 
    login: () => HOST + MEMBER + 'localLogin',

    // 회원 소셜 로그인
    socialLogin: () => HOST + 'members/socialLogin',

    // 회원 비밀번호 변경
    changePassword : function(member_id: number) {
      return HOST + MEMBER + 'password/' + member_id
    },

    // 회원 정보 조회/ 수정/ 탈퇴
    member: function(member_id: number) {
      return HOST + MEMBER + member_id
    },

    // 회원 알러지
    memberAllergy: function(member_id: number) {
      return HOST + MEMBER + ALLERGY + member_id
    },

    // 회원 비선호 재료
    memberDislikeIngredient: function(member_id: number) {
      return HOST + MEMBER + DISLIKE + member_id
    },

    // 회원 장비
    memberUtensil: function(member_id: number) {
      return HOST + MEMBER + UTENSIL + member_id
    },

    // 회원 이메일 중복 확인
    emailDuplicate: function(member_id: number) {
      return HOST + MEMBER + 'duplicate/' + member_id
    },

    // 회원 이메일 인증
    emailAuth: () => HOST + MEMBER + 'emailauth',

    // 회원 레시피 좋아요
    memberRecipeLike: function(member_id: number) {
      return HOST + RECIPES + 'like/' + member_id
    },
    // 회원 최근 본 레시피
    memberRecipeLatest: function(member_id: number) {
      return HOST + 'recipes/Latest/' + member_id
    },

    // 회원 양념 (조회/ 수정/ 삭제)
    memberSeasoning: function(member_id: number) {
      return HOST + MEMBER + member_id +'/' + 'seasoning/'
    } 
  },

  recipe:{
    // 레시피 전체 조회
    fetchRecipe: () => HOST + 'recipes',
  
    // 레시피 특정 조회
    specificRecipe: () => HOST + RECIPES + 'want/' ,
  
    // 레시피 상세 보기
    detailRecipe: function(recipe_id: number) {
      return HOST + RECIPES + recipe_id
    },
  
    // 레시피 맞춤 추천
    recommendRecipe: () => HOST + RECIPES + 'recomment',
  
    // 레시피 재료 유사도 
    similarityRecipe: () => HOST + RECIPES + 'similarity',
  
    // 레시피 음성 출력
    recipeVoice: function(recipe_id: number) {
      return HOST + RECIPES + 'voice/' + recipe_id
    },
  

  },
  
  atc:{
    // 기타 - 캘린더에서 한 달간 사용한 레시피 횟수 확인
    calendarCount: function(member_id: number) {
      return HOST + RECIPES + CALENDAR + member_id
    },

    // 기타 - 캘린더에서 특정 날짜에 먹은 레시피 확인
    calendarEat: function(member_id: number) {
      return HOST + RECIPES + CALENDAR + 'date/' + member_id
    },
    // 기타 - 재료 조회
    ingredient: () => HOST + 'ingredient'
  },
};
