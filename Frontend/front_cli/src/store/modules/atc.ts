import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';
// ATC 모듈의 상태 정의
interface ATCState {
  // ATC 상태 정의
  ingredient: Ingredient [];
  ingredientsymnonym: IngredientSymnonym [];
  seasoning: Seasoning [];
  seasoningsymnonym: SeasoningSymnonym [];
  allergyingredient: AllergyIngredient [];
  allergy: Allergy [];
  calendar: Calendar [];
  utensil: Utensil [];
  refregirator : Refregirator [];
  specificIngredient: SpecificIngredient[];
}

// 재료
interface Ingredient {
  ingredient_id: number
  title: string
  kor: string
  eng: string
  def: string
  level: number
  upperClass: string
  superUpperClass: string
  etc: string
}

// 재료 동의어
interface IngredientSymnonym {
  ingredient_symnonym_id: number
  word: string
  ingredient_id: number
  code: any
}

// 양념
interface Seasoning {
  seasoning_id: number
  name: string
  kor: string
  eng: string
  definition: string
  level: number
  upperClass: string
  superUpperClass: string
  etc: string
}

// 양념 동의어
interface SeasoningSymnonym {
  seasoning_symnonym: number
  word: string
  seasoning_id: number
  code: any
}

// 알러지 재료
interface AllergyIngredient {
  allergy_ingredient_id: number
  allergy_id: number
  ingredient_id2: number
}

// 알러지
interface Allergy {
  allergy_id: number
  name: string
}

// 캘린더
interface Calendar {
  calendar_id: number
  member_id: number
  recipe_id: number
  date: Date
  seq: number
}

// 장비
interface Utensil{
  utensil_id: number
  name: string
  way: number
}

// 냉장고
interface Refregirator {
  refregirator_id: number
  member_id: number
  ingredients_id: number
  storage: number
  // DECIMAL
  amount: string
  unit: string
  start_date: Date
  expired_date: Date
}

// 특정 재료 조회
interface SpecificIngredient{
  ingredientId: number
  name: String
}

const atc: Module<ATCState, RootState> = {
  state: {
    ingredient: [],
    ingredientsymnonym: [],
    seasoning: [],
    seasoningsymnonym: [],
    allergyingredient: [],
    allergy: [],
    calendar: [],
    utensil: [],
    refregirator: [],
    specificIngredient: [],
  },
  getters: {
    ingredient: state => state.ingredient,
    seasoning: state => state.seasoning,
    searchIngredient: state => state.specificIngredient
    // ATC 게터 정의
  },
  mutations: {
    GET_INGREDIENT: (state, ingredient) => (state.ingredient = ingredient),
    // ATC 뮤테이션 정의
    SET_SPECIFIC_INGREDIENT: (state, ingredient) => (state.ingredient = ingredient)
  },
  actions: {
    fetchIngredient({ commit, getters }) {
      axios({
        url: api.atc.ingredient(),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res => {
          commit('GET_INGREDIENT', res.data);
        })
        .catch(err => console.log(err.response));
    },
    async specificIngredient({ commit }, name) {
      try {
        console.log('재료 조회 시작!', name);
        const response = await axios.get('https://i9b202.p.ssafy.io/api/foods/ingredient/search', {
          params: {
            name: name,
          },
        });
  
        console.log('특정 재료 조회 성공!', response.data);
        commit('SET_SPECIFIC_INGREDIENT', response.data);
      } catch (error) {
        console.error('특정 재료 조회 실패!', error);
      }
    },
  },
};

export default atc;
