import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
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
    recipe: [
      {
        recipe_id: 1,
        name: "오므라이스",
        // way: 2,
        // weight: 300,
        // calorie: 500,
        // carbohydrate: 50,
        // protein: 20,
        // fat: 15,
        // salt: 1,
        // tag: "한식",
        img_small: "https://example.com/omurice_small.jpg",
        img_big: "https://example.com/omurice_big.jpg",
        category: "밥",
        likes: 1,
        // dish: 1
      },
      {
        recipe_id: 2,
        name: "스파게티",
        // way: 3,
        // weight: 350,
        // calorie: 600,
        // carbohydrate: 70,
        // protein: 25,
        // fat: 18,
        // salt: 2,
        // tag: "양식",
        img_small: "https://example.com/spaghetti_small.jpg",
        img_big: "https://example.com/spaghetti_big.jpg",
        category: "일품",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 3,
        name: "짜장면",
        // way: 2,
        // weight: 400,
        // calorie: 550,
        // carbohydrate: 60,
        // protein: 22,
        // fat: 17,
        // salt: 1.5,
        // tag: "중식",
        img_small: "https://example.com/jjajangmyeon_small.jpg",
        img_big: "https://example.com/jjajangmyeon_big.jpg",
        category: "일품",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 4,
        name: "삼겹살",
        // way: 1,
        // weight: 200,
        // calorie: 800,
        // carbohydrate: 0,
        // protein: 40,
        // fat: 60,
        // salt: 3,
        // tag: "한식",
        img_small: "https://example.com/samgyeopsal_small.jpg",
        img_big: "https://example.com/samgyeopsal_big.jpg",
        category: "반찬",
        // dish: 1
        likes: 1, 
      },
      {
        recipe_id: 5,
        name: "피자",
        // way: 4,
        // weight: 500,
        // calorie: 900,
        // carbohydrate: 100,
        // protein: 30,
        // fat: 40,
        // salt: 2.5,
        // tag: "양식",
        img_small: "https://example.com/pizza_small.jpg",
        img_big: "https://example.com/pizza_big.jpg",
        category: "일품",
        // dish: 1,
        likes: 1,
      },
      {
        recipe_id: 6,
        name: "삼색 파스타",
        // way: 3,
        // weight: 400,
        // calorie: 700,
        // carbohydrate: 80,
        // protein: 25,
        // fat: 20,
        // salt: 1.5,
        // tag: "이탈리아",
        img_small: "https://example.com/samsaek_pasta_small.jpg",
        img_big: "https://example.com/samsaek_pasta_big.jpg",
        category: "일품",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 7,
        name: "불고기",
        // way: 1,
        // weight: 300,
        // calorie: 600,
        // carbohydrate: 50,
        // protein: 30,
        // fat: 25,
        // salt: 1.5,
        // tag: "한식",
        img_small: "https://example.com/bulgogi_small.jpg",
        img_big: "https://example.com/bulgogi_big.jpg",
        category: "반찬",
        // dish: 1,
        likes: 1,
      },
      {
        recipe_id: 8,
        name: "샐러드",
        // way: 5,
        // weight: 200,
        // calorie: 300,
        // carbohydrate: 20,
        // protein: 10,
        // fat: 5,
        // salt: 0.5,
        // tag: "다이어트",
        img_small: "https://example.com/salad_small.jpg",
        img_big: "https://example.com/salad_big.jpg",
        category: "반찬",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 9,
        name: "짬뽕",
        // way: 2,
        // weight: 400,
        // calorie: 600,
        // carbohydrate: 70,
        // protein: 25,
        // fat: 15,
        // salt: 2,
        // tag: "중식",
        img_small: "https://example.com/jjamppong_small.jpg",
        img_big: "https://example.com/jjamppong_big.jpg",
        category: "일품",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 10,
        name: "카레라이스",
        // way: 2,
        // weight: 350,
        // calorie: 550,
        // carbohydrate: 60,
        // protein: 20,
        // fat: 15,
        // salt: 1.5,
        // tag: "일식",
        img_small: "https://example.com/kare_rice_small.jpg",
        img_big: "https://example.com/kare_rice_big.jpg",
        category: "밥",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 11,
        name: "계란말이",
        // way: 1,
        // weight: 200,
        // calorie: 250,
        // carbohydrate: 5,
        // protein: 15,
        // fat: 20,
        // salt: 0.5,
        // tag: "한식",
        img_small: "https://example.com/gyeran_mari_small.jpg",
        img_big: "https://example.com/gyeran_mari_big.jpg",
        category: "반찬",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 12,
        name: "라면",
        // way: 2,
        // weight: 450,
        // calorie: 500,
        // carbohydrate: 50,
        // protein: 10,
        // fat: 20,
        // salt: 3,
        // tag: "간편식",
        img_small: "https://example.com/ramen_small.jpg",
        img_big: "https://example.com/ramen_big.jpg",
        category: "일품",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 13,
        name: "초밥",
        // way: 3,
        // weight: 350,
        // calorie: 600,
        // carbohydrate: 80,
        // protein: 25,
        // fat: 15,
        // salt: 1.5,
        // tag: "일식",
        img_small: "https://example.com/sushi_small.jpg",
        img_big: "https://example.com/sushi_big.jpg",
        category: "밥",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 14,
        name: "닭볶음탕",
        // way: 1,
        // weight: 350,
        // calorie: 600,
        // carbohydrate: 20,
        // protein: 30,
        // fat: 25,
        // salt: 2,
        // tag: "한식",
        img_small: "https://example.com/dak_bokkeumtang_small.jpg",
        img_big: "https://example.com/dak_bokkeumtang_big.jpg",
        category: "반찬",
        // dish: 1
        likes: 1,
      },
      {
        recipe_id: 15,
        name: "햄버거",
        // way: 4,
        // weight: 250,
        // calorie: 700,
        // carbohydrate: 60,
        // protein: 25,
        // fat: 30,
        // salt: 2.5,
        // tag: "양식",
        img_small: "https://example.com/hamburger_small.jpg",
        img_big: "https://example.com/hamburger_big.jpg",
        category: "후식",
        // dish: 1
        likes: 1,
      },
    ],
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
    recipeSpecific ({commit}, content) {
      console.log(content, '레시피 특정 조회 시작!')
      axios ({
        url: api.recipe.specificRecipe(),
        method: 'get',
        data: content,
      })
        .then (res=> {
          console.log('레시피 특정 조회 성공!')
          console.log(res)
          commit('SET_RECIPE_SPECIFIC', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    // 레시피 상세 조회
    detailRecipe ({commit, getters}, recipe_id) {
      axios({
        url: api.recipe.detailRecipe(recipe_id),
        method:'get',
        headers: getters.authHeader,
      })
        .then(res=> {
          commit('SET_RECIPE_DETAIL', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    }
  },

}

export default recipe;
