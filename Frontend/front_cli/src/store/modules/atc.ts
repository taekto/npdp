import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';
// ATC 모듈의 상태 정의
interface ATCState {
  // ATC 상태 정의
  ingredient: Ingredient[];
}

interface Ingredient {
  ingredient_id: number
  name: string
}

const atc: Module<ATCState, RootState> = {
  state: {
    ingredient: [
      // 임시 데이터 ( 삭제해야함 )
      { ingredient_id: 1, name: '재료 1' },
      { ingredient_id: 2, name: '재료 2' },
      { ingredient_id: 3, name: '재료 3' },
      { ingredient_id: 4, name: '치킨' },
      { ingredient_id: 5, name: '꽁치' },
      { ingredient_id: 6, name: '통조림' },
      { ingredient_id: 7, name: '담배' },
      { ingredient_id: 8, name: '술' },
      { ingredient_id: 10, name: '재료 4' },
      { ingredient_id: 9, name: '재리' },
      { ingredient_id: 11, name: '재료 5' },
      { ingredient_id: 13, name: '재료 6' },
      { ingredient_id: 12, name: '재료 7' },
      { ingredient_id: 14, name: '재료 8' },
      { ingredient_id: 15, name: '재료 9' },
      { ingredient_id: 16, name: '재료 10' },
    ],
    // ATC 상태 초기화
  },
  getters: {
    ingredient: state => state.ingredient,
    // ATC 게터 정의
  },
  mutations: {
    GET_INGREDIENT: (state, ingredient) => (state.ingredient = ingredient),
    // ATC 뮤테이션 정의
  },
  actions: {
    fetchIngredient({ commit, getters }) {
      axios({
        url: api.recipe.ingredient(),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('GET_INGREDIENT', res.data);
        })
        .catch(err => console.log(err.response));
    },
    // ATC 액션 정의
  },
};

export default atc;
