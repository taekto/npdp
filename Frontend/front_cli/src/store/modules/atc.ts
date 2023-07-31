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

const atc: Module<ATCState, RootState> = {
  state: {
    ingredient: [
      // 임시 데이터 ( 삭제해야함 )
      {
        ingredient_id: 1,
        title: '재료 1',
        kor: '재료 1 한글',
        eng: 'Ingredient 1',
        def: '재료 1에 대한 설명',
        level: 1,
        upperClass: '상위 분류 1',
        superUpperClass: '최상위 분류 1',
        etc: '기타 정보 1',
      },
      {
        ingredient_id: 2,
        title: '재료 2',
        kor: '재료 2 한글',
        eng: 'Ingredient 2',
        def: '재료 2에 대한 설명',
        level: 2,
        upperClass: '상위 분류 2',
        superUpperClass: '최상위 분류 1',
        etc: '기타 정보 2',
      },
      {
        ingredient_id: 3,
        title: '재료 3',
        kor: '재료 3 한글',
        eng: 'Ingredient 3',
        def: '재료 3에 대한 설명',
        level: 3,
        upperClass: '상위 분류 3',
        superUpperClass: '최상위 분류 1',
        etc: '기타 정보 3',
      },
      {
        ingredient_id: 4,
        title: '치킨',
        kor: '치킨 한글',
        eng: 'Chicken',
        def: '치킨에 대한 설명',
        level: 2,
        upperClass: '육류',
        superUpperClass: '최상위 분류 1',
        etc: '기타 정보 4',
      },
      {
        ingredient_id: 5,
        title: '꽁치',
        kor: '꽁치 한글',
        eng: 'Mackerel',
        def: '꽁치에 대한 설명',
        level: 3,
        upperClass: '수산물',
        superUpperClass: '최상위 분류 1',
        etc: '기타 정보 5',
      },
    ],
    ingredientsymnonym: [],
    seasoning: [ 
      {
      seasoning_id: 1,
      name: "소금",
      kor: "소금",
      eng: "Salt",
      definition: "나트륨과 염소로 이루어진 화학 물질로, 음식에 맛을 내는 데에 사용됩니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "식품 조리에서 주로 사용되며 맛을 강조합니다.",
    },
    {
      seasoning_id: 2,
      name: "후추",
      kor: "후추",
      eng: "Black Pepper",
      definition: "페퍼나무의 열매를 말리고 갈아서 만든 가루로, 음식에 감칠맛을 더합니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "많은 요리에서 사용되며 감칠맛을 높입니다.",
    },
    {
      seasoning_id: 3,
      name: "간장",
      kor: "간장",
      eng: "Soy Sauce",
      definition: "대두와 밀을 발효시켜 만든 음식으로, 소금과 함께 많은 요리에 사용됩니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "많은 요리에서 소스의 기본 재료로 사용됩니다.",
    },
    {
      seasoning_id: 4,
      name: "식초",
      kor: "식초",
      eng: "Vinegar",
      definition: "과일 또는 곡물을 발효시켜 만든 산미가 있는 액체로, 음식에 신맛을 더합니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "많은 요리에서 신맛을 내는 데에 사용됩니다.",
    },
    {
      seasoning_id: 5,
      name: "고추장",
      kor: "고추장",
      eng: "Red Pepper Paste",
      definition: "고추를 발효시켜 만든 간장으로, 음식에 매운맛을 더합니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "많은 요리에서 매운맛을 내는 데에 사용됩니다.",
    },
    {
      seasoning_id: 6,
      name: "설탕",
      kor: "설탕",
      eng: "Sugar",
      definition: "사탕수수를 증발시켜 만든 단맛이 있는 물질로, 음식에 단맛을 더합니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "다양한 요리와 음료에서 단맛을 내는 데에 사용됩니다.",
    },
    {
      seasoning_id: 7,
      name: "다시다",
      kor: "다시다",
      eng: "Dashi",
      definition: "국물 또는 스프의 기본으로 사용되는 일본의 육수 또는 물로 만든 양념입니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "일본 요리에서 많이 사용되는 양념으로 간을 조절합니다.",
    },
    {
      seasoning_id: 8,
      name: "맛술",
      kor: "맛술",
      eng: "Mirin",
      definition: "일본의 간장으로 사용되는 달콤하고 약간의 알코올이 있는 품질 술입니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "일본 요리에서 간을 조절하는 데에 사용됩니다.",
    },
    {
      seasoning_id: 9,
      name: "케첩",
      kor: "케첩",
      eng: "Ketchup",
      definition: "토마토로 만든 단맛과 산미가 있는 양념으로, 프렌치 프라이와 핫도그 등에 많이 사용됩니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "식물 기반",
      etc: "다양한 요리에서 양념 또는 소스로 사용됩니다.",
    },
    {
      seasoning_id: 10,
      name: "버터",
      kor: "버터",
      eng: "Butter",
      definition: "우유의 유지로 만든 동물성 기름으로, 요리나 베이킹에 사용됩니다.",
      level: 1,
      upperClass: "조미료",
      superUpperClass: "동물 기반",
      etc: "다양한 요리와 베이킹에서 사용되며 풍부한 맛을 더합니다.",
    },
  ],
    seasoningsymnonym: [],
    allergyingredient: [],
    allergy: [],
    calendar: [],
    utensil: [],
    refregirator: [],
  },
  getters: {
    ingredient: state => state.ingredient,
    seasoning: state => state.seasoning
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
