const HOST = 'http://i9b202.p.ssafy.io:8080/api/';

const MEMBER = 'members/';
const RECIPE = 'recipe/';
const ATC = 'atc/';


export default {
  recipe: {
    ingredient: function() {
      return HOST + ATC + 'ingredient/';
    },
  },

  // member_id or nickname
  member: {
    // 회원가입
    signup:() => HOST + 'members',

    // 로그인 
    login: () => HOST + 'members/localLogin',

    // 소셜 로그인

    socialLogin: () => HOST + 'members/socialLogin',

    // 회원 정보
    member: function(member_id: number) {
      return HOST + 'members/' + member_id
    },

    // 레시피 좋아요
    memberRecipeLike: function(member_id: number) {
      return HOST + 'recipes/like/' + member_id
    },

    // 최근 본 레시피
    memberRecipeLatest: function(member_id: number) {
      return HOST + 'recipes/Latest/' + member_id
    },

    // 비선호 레시피
    memberDislikeIngredient: function(nickname: string) {
      return HOST + MEMBER + nickname + '/' + 'dislikeingredient/'
    },

    // 회원 알러지
    memberAllergy: function(nickname:string) {
      return HOST + MEMBER + nickname + +'/' + 'allergy/'
    },

    // 회원 장비
    memberUtensil: function(nickname:string) {
      return HOST + MEMBER + nickname + '/' + 'utensil/'
    },

    // 회원 양념
    memberSeasoning: function(nickname:string) {
      return HOST + MEMBER + nickname +'/' + 'seasoning/'
    }
  },

};
