import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router/index';
import api from '@/api/api';
import axios from 'axios';
import { AnyObject } from 'chart.js/dist/types/basic';

interface RecipeState {
  recipe: Recipe[];
  recipeSequence: RecipeSequence[];
  recipeUtensil: RecipeUtensil[];
  recipeSeasoning: RecipeSeasoning[];
  recipeIngredient: RecipeIngredient[];
  recipeSpecific: RecipeSpecific[];
  recipeDetail: RecipeDetail[];
  recipeRecommend: RecipeRecommend[];
}

// 레시피
interface Recipe {
  recipe_id: number
  name: string
  // way: number
  // weight: number
  // calorie: number
  // carbohydrate: number
  // protein: number
  // fat: number
  // salt: number
  // tag: string
  img_small: string
  img_big: string
  category: string
  // dish: number
  likes: number
}
// 과정
interface RecipeSequence {
  procedure_id:number
  recipe_id: number
  sequence: number
  description: string
  img: string
}

// 레시피 조리장비
interface RecipeUtensil {
  recipe_utensil_id: number
  recipe_id: number
  utensil_id: number
}

// 레시피 포함 양념
interface RecipeSeasoning {
  seasoning_inclusion_id: number
  recipe_key: number
  seasoning_id: number
}

// 레시피 포함재료
interface RecipeIngredient {
  ingredient_inclusion_id: number
  recipe_key: number
  ingredients_key: number
  type: number
  amount: number
  unit: string
  etc?: string
}

// 레시피 특정
interface RecipeSpecific {
  recipe_id: number
  name: string
  img: string
}

// 레시피 상세
interface RecipeDetail {
  name: string
  way: number
  weight: number
  calorie: number
  carbohydrate: number
  protein: number
  fat: number
  salt: number
  img: string
  // recipe_id: number
  // tag: string
  // img_small: string
  // img_big: string
  // category: string
  // dish: number
}

// 레시피 맞춤 추천
interface RecipeRecommend {
  ingredient: Array<{
    ingredientId: number; 
  }>;
  seasoning: Array<{
    seasoningId: number; 
  }>;
  taste: Array<{
    ingredientId: number; 
  }>;
}


const recipe: Module<RecipeState, RootState> = {
  state: {
    recipe: [],
    recipeSpecific: [],
    recipeSequence: [],
    recipeUtensil: [],
    recipeSeasoning: [],
    recipeIngredient: [],
    recipeDetail: [],
    recipeRecommend: [],
  },

  getters: {
    recipe: state => state.recipe,
    recipeSpecific: state => state.recipeSpecific,
    recipeDetail: state => state.recipeDetail,
  },

  mutations: {
    SET_RECIPE: (state, recipe) => (state.recipe = recipe),
    SET_RECIPE_SPECIFIC: (state, recipeSpecific) => (state.recipeSpecific = recipeSpecific),
    SET_RECIPE_DETAIL: (state, recipeDetail) => (state.recipeDetail = recipeDetail),
  },

  actions: {
    // 레시피 전체 조회
    fetchRecipe ({commit}) {
      console.log('get요청 보내기')
      axios ({
        url:api.recipe.fetchRecipe(),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res=> {
          console.log(res)
          commit('SET_RECIPE', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    // 레시피 특정 조회
    recipeSpecificSearch ({commit}, content) {
      console.log(content, '레시피 특정 조회 시작!')
      console.dir(api.recipe.specificRecipe()),
      axios ({
        //url: api.recipe.specificRecipe(),
        url: `https://i9b202.p.ssafy.io/api/recipes/want`,
        method: 'get',
        params: {
          content: content 
        },
        
      })
      .then (res=> {
        console.log('레시피 특정 조회 성공!')
        console.log(res)
        commit('SET_RECIPE_SPECIFIC', res.data)
        router.push({
          name: 'searchKeyword',
          params: {
            keyword: content,
          },
        });
      })
      .catch(err => {
        console.log('레시피 특정 조회 실패..')
        console.log(err.response)
      })
    },

    // 레시피 상세 조회
    detailRecipe ({commit}, recipe_id) {
      console.log('레시피 상세 조회 시작!', recipe_id)
      axios({
        // url: api.recipe.detailRecipe(recipe_id),
        url: `https://i9b202.p.ssafy.io/api/recipes/${recipe_id}`,
        method:'get',

        // headers: getters.authHeader,
      })
        .then(res=> {
          console.log(res.data, '레시피 상세 조회 성공!')
          commit('SET_RECIPE_DETAIL', res.data)
        })
        .catch(err => {
          console.log('레시피 상세 조회 실패....')
          console.log(err.response)
        })
    }
  },

}

export default recipe;
