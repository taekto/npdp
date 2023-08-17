import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';

interface RefrigeratorState {
    ingredients: Ingredient[];
    seasonings: Seasoning[];
}

interface Ingredient {
    pk : number
    name: string
    amount: number
    unit: string
    startDate: string
    endDate: string
    storage: string
}

interface Seasoning {
    pk : number
    name: string
    startDate: string
    endDate: string
    storage: string
}




const refrigerator: Module<RefrigeratorState, RootState> = {
    state: {
        ingredients : [
            {pk: 0, name : "감자", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
            {pk: 1,name : "김치", amount: 2, unit: "포기",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 2,name : "계란", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 3,name : "돼지고기", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 4,name : "소고기", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 5,name : "양파", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉동'}, 
            {pk: 6,name : "닭", amount: 2, unit: "마리",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 7,name : "대파", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 8,name : "칡", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 9,name : "마", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 10,name : "라면", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '실온'}, 
            {pk: 11,name : "김", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '실온'},
        ],
        seasonings : [{pk: 0, name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
            {pk: 1, name : "진간장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 2,name : "고추장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 3,name : "된장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
            {pk: 4,name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉동'}, 
            {pk: 5,name : "다진마늘",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 6,name : "다시마",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
            {pk: 7,name : "멸치액젓", startDate : '2023-07-27', endDate: '', storage: '실온'}, 
            {pk: 8,name : "양조간장",  startDate : '2023-07-27', endDate: '', storage: '실온'},
            {pk: 9,name : "초고추장",  startDate : '2023-07-27', endDate: '', storage: '실온'},
            {pk: 10,name : "케쳡",  startDate : '2023-07-27', endDate: '', storage: '실온'},],
    },
    getters: {
        ingredients: state => state.ingredients,
        seasonings: state => state.seasonings
    },
    mutations: {
        PLUS_AMOUNT: (state, tempIngredient) => {state.ingredients = tempIngredient}
            
    },
    actions: {
        plusAmount({ commit, getters }, tmpingredient) {
            const tempIngredient = [...getters.ingredients]; // 배열을 복사하여 변경
            for (let i = 0; i < tempIngredient.length; i++) {
                if (tmpingredient.pk === tempIngredient[i].pk) {
                    if (tempIngredient[i].unit === "g") {
                        tempIngredient[i].amount += 100;
                    } else {
                        tempIngredient[i].amount++;
                    }
                }
            }
            commit('PLUS_AMOUNT', tempIngredient); // 변경된 배열을 스토어에 커밋
        }
            
        }
    }


export default refrigerator
