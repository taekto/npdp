const HOST = 'http://localhost:8000/api/v1/';
const TMP = 'tmp/';
const USER = 'user/';

export default {
  ingredientsList: function() {
    return HOST + TMP;
  },
  userDisfavor: function(userPk: number, ingredientId: number) {
    return HOST + USER + userPk + '/' + ingredientId;
  },
};
