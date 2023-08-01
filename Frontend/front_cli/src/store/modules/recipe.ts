import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';

interface RecipeState {
  recipe: Recipe[];
  recipeSequence: RecipeSequence[];
  recipeUtensil: RecipeUtensil[];
  recipeSeasoning: RecipeSeasoning[];
  recipeIngredient: RecipeIngredient[];
}

// 레시피
interface Recipe {
  recipe_id: number
  name: string
  way: number
  weight: number
  calorie: number
  carbohydrate: number
  protein: number
  fat: number
  salt: number
  tag: string
  img_small: string
  img_big: string
  category: string
  dish: number
}
// 과정
interface RecipeSequence {
  procedure_id:number
  recipe_id: number
  procedure: number
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
  have_id: number
  member_id: number
  type: number
  amount: number
  unit: string
  Field?: string
}
const recipe: Module<RecipeState, RootState> = {
  state: {
    recipe: [],
    recipeSequence: [],
    recipeUtensil: [],
    recipeSeasoning: [],
    recipeIngredient: [],
  },
  getters: {
    
  },
  mutations: {
    
  },
  actions: {
    
  },
};

export default recipe;
