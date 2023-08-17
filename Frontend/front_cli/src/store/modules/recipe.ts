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
  recipeWay: RecipeWay[];
  selectCategory: string;
  selectClassification: string;
  recipeSimilarity: RecipeSimilarity[];
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
  // likes: number
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

interface RecipeWay {
  recipe_way_id : number
  recipe_way_name: string
}

interface RecipeSimilarity {
  recipeId: number
  name: string
  imgBig: string
  imgSmall: string
  category: string
  similarity: number
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
    recipeWay: [{recipe_way_id : 1, recipe_way_name : '굽기'},
    {recipe_way_id : 2, recipe_way_name : '끓이기'},
    {recipe_way_id : 3, recipe_way_name : '볶기'},
    {recipe_way_id : 4, recipe_way_name : '찌기'},
    {recipe_way_id : 5, recipe_way_name : '튀기기'},
    {recipe_way_id : 6, recipe_way_name : '기타'},],
    selectCategory: "",
    selectClassification: "",
    recipeSimilarity: [],
  },

  getters: {
    recipe: state => state.recipe,
    recipeSpecific: state => state.recipeSpecific,
    recipeDetail: state => state.recipeDetail,
    recipeWay: state => state.recipeWay,
    selectCategory: state => state.selectCategory,
    selectClassification: state => state.selectClassification,
    recipeSimilarity: state => state.recipeSimilarity
  },

  mutations: {
    SET_RECIPE: (state, recipe) => (state.recipe = recipe),
    SET_RECIPE_SPECIFIC: (state, recipeSpecific) => (state.recipeSpecific = recipeSpecific),
    SET_RECIPE_DETAIL: (state, recipeDetail) => (state.recipeDetail = recipeDetail),
    SET_CATEGORY_CHOICE: (state, choice) => (state.selectCategory = choice),
    SET_CLASSIFICATION_CHOICE: (state, choice) => (state.selectClassification = choice),
    SET_RECIPE_SIMILARITY: (state, similarity) => (state.recipeSimilarity= similarity),
  },

  actions: {
    // 레시피 전체 조회
    fetchRecipe ({commit}) {

      axios ({
        url:api.recipe.fetchRecipe(),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res=> {
          commit('SET_RECIPE', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    // 레시피 특정 조회
    recipeSpecificSearch ({commit}, content) {

      axios ({
        url: `https://i9b202.p.ssafy.io/api/recipes/want`,
        method: 'get',
        params: {
          content: content 
        },
        
      })
      .then (res=> {


        commit('SET_RECIPE_SPECIFIC', res.data)
        
        router.push({
          name: 'searchKeyword',
          params: {
            keyword: content,
          },
        });
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 레시피 상세 조회
    detailRecipe ({commit, dispatch}, {recipeId, memberId}) {
      axios({
        // url: api.recipe.detailRecipe(recipe_id),
        url: 'https://i9b202.p.ssafy.io/api/recipes/detail',
        method:'post',
        data: {
          recipeId: recipeId,
          memberId: memberId
        },
        // headers: getters.authHeader,
      })
        .then(res=> {

          commit('SET_RECIPE_DETAIL', res.data)  
          const lookData = { memberId: memberId, recipeId: res.data.recipeId}
          const recipeOwnId = {recipeOwnId: res.data.recipeId}
          dispatch('recipeSimilarity', recipeOwnId)
          dispatch('latestRecipe', {type:'post', lookData: lookData})
          commit('SET_IS_RECIPE_LIKE', res.data.heartTF)   
            router.push({name: "recipe",  
              params: { 
                recipeId: recipeId,
              },
          })
        })
        .catch(err => {
          console.log(err)
        })
    },

    async fetchRecipes(context) {
      try {
        const response = await axios.get('https://i9b202.p.ssafy.io/api/members/heart/count');
        context.commit('SET_RECIPE', response.data);
      } catch (error) {
        console.error('Error fetching recipes:', error);
      }
    },

    // 레시피 동적 서치
    async querySearch({commit}, data) {
      try {
        const apiUrl = 'https://i9b202.p.ssafy.io/api/recipes/category'


        const res = await axios.post(apiUrl, data)

        commit('SET_RECIPE_SPECIFIC', res.data)
      } catch (err) { 
        console.log(err)
      }
      // console.log('동적 서치 시작!',data)
      // axios({
      //   url: 'https://i9b202.p.ssafy.io/api/recipes/category',
      //   method: 'post',
      //   data: {
      //     searchWord: data.searchWord,
      //     classification: data.classification,
      //     keyWord: data.keyWord,
      //   },
      // })
      // .then (res => {
      //   console.log(res)
      //   commit('SET_RECIPE_SPECIFIC', res.data)
        
      //   router.push({
      //     name: 'searchKeyword',
      //     params: {
      //       keyword: data.searchWord,
      //     },
      //   })
      // })
      // .catch (err => {
      //   console.log(err)
      // })
    },
    // 레시피 재료 유사도
    async recipeSimilarity({commit}, recipeOwnId) {
      try {
        const res = await axios.post('https://i9b202.p.ssafy.io/api/recipes/similarity', recipeOwnId)
        commit('SET_RECIPE_SIMILARITY', res.data)

      } catch(err) {
        console.log(err)
      }
    },
  },

}

export default recipe;
