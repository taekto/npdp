const HOST = 'http://localhost:8000/api/v1/';

const MEMBER = 'member/';
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
    memberDislikeIngredient: function(nickname: string) {
      return HOST + MEMBER + nickname + '/' + 'dislikeingredient/';   
    },
    member: function(nickname: string) {
      return HOST + MEMBER + nickname +'info/'
    },
    memberAllergy: function(nickname:string) {
      return HOST + MEMBER + nickname + 'allergy/'
    }
  },

};
