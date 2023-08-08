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

// 레시피 특정-
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
  recipe_id: number
  // tag: string
  img_small: string
  img_big: string
  category: string
  dish: number
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
        "recipe_id": 1,
        "name": "Spaghetti Bolognese",
        "img_small": "path_to_small_image_1.jpg",
        "img_big": "path_to_big_image_1.jpg",
        "category": "Pasta",
        "likes": 100
      },
      {
        "recipe_id": 2,
        "name": "Grilled Chicken Salad",
        "img_small": "path_to_small_image_2.jpg",
        "img_big": "path_to_big_image_2.jpg",
        "category": "Salad",
        "likes": 50
      },
      {
        "recipe_id": 3,
        "name": "Cheese Pizza",
        "img_small": "path_to_small_image_3.jpg",
        "img_big": "path_to_big_image_3.jpg",
        "category": "Pizza",
        "likes": 200
      },
      {
        "recipe_id": 4,
        "name": "Stir-Fried Vegetables",
        "img_small": "path_to_small_image_4.jpg",
        "img_big": "path_to_big_image_4.jpg",
        "category": "Vegetables",
        "likes": 80
      },
      {
        "recipe_id": 5,
        "name": "Chicken Curry",
        "img_small": "path_to_small_image_5.jpg",
        "img_big": "path_to_big_image_5.jpg",
        "category": "Curry",
        "likes": 120
      },
      {
        "recipe_id": 6,
        "name": "Burger",
        "img_small": "path_to_small_image_6.jpg",
        "img_big": "path_to_big_image_6.jpg",
        "category": "Fast Food",
        "likes": 300
      },
      {
        "recipe_id": 7,
        "name": "Tacos",
        "img_small": "path_to_small_image_7.jpg",
        "img_big": "path_to_big_image_7.jpg",
        "category": "Mexican",
        "likes": 90
      },
      {
        "recipe_id": 8,
        "name": "Sushi Roll",
        "img_small": "path_to_small_image_8.jpg",
        "img_big": "path_to_big_image_8.jpg",
        "category": "Sushi",
        "likes": 250
      },
      {
        "recipe_id": 9,
        "name": "Fried Rice",
        "img_small": "path_to_small_image_9.jpg",
        "img_big": "path_to_big_image_9.jpg",
        "category": "Rice",
        "likes": 70
      },
      {
        "recipe_id": 10,
        "name": "Tomato Soup",
        "img_small": "path_to_small_image_10.jpg",
        "img_big": "path_to_big_image_10.jpg",
        "category": "Soup",
        "likes": 110
      }
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
