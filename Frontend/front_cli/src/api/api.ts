const HOST = 'http://localhost:8000/api/v1/';
const USER = 'user/';
const RECIPE = 'recipe/';

export default {
  recipe: {
    ingredientList: function() {
      return HOST + RECIPE + 'ingredientlist';
    },
  },
  user: {
    userDisfavorList: function(userPk: number) {
      return HOST + USER + userPk + '/' + 'disfavorlist/';   
    },
    currentUserInfo: function() {
      return HOST + USER
    },
  },
};
