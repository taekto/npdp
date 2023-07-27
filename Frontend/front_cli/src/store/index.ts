import { createStore } from 'vuex'
import axios from 'axios'
import api from '@/api/api'
import router from '@/router'

interface State {
  token: string
  ingredient: Ingredient[]
  memberDislikeIngredient: MemberDislikeIngredient[]
  Member: Member[]
  memberAllergy: MemberAllergy[]
}

interface Member {
  member_id: number
  email: string
  nickname: string
  password: string
  oauth: string
  refresh_token: string
  role: number
  birth: string
  gender: string
  quit: boolean
}

interface Ingredient {
  ingredient_id: number
  name: string
}

interface MemberDislikeIngredient {
  member_dislike_ingredient_id:number
  member_id: number
  ingredient_id: number
}

// 알러지 추후 수정 필요 have_id??
// 알러지 action 작성 필요 CRUD
interface MemberAllergy {
  have_id: number
  member_id: number
  ingredient_id: number
}

const store = createStore<State>({
  state: {
    token: localStorage.getItem('token') || '',
    Member: [],
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
    memberDislikeIngredient: [],
    memberAllergy: [
      // 임시 데이터 (삭제해야 함)
      // 알러지 action 작성 필요 CR(작성)UD
      {have_id: 1, member_id: 1, ingredient_id: 6},
      {have_id: 2, member_id: 1, ingredient_id: 7},
      {have_id: 3, member_id: 1, ingredient_id: 4},
    ],
    
  },
  getters: {
    Member: state => state.Member,
    isLoggedIn: state => !!state.token,
    authHeader: state => ({ Authorization: `Token ${state.token}` }),
    ingredient: state => state.ingredient,
    memberDislikeIngredient: state => state.memberDislikeIngredient,
    memberAllergy: state => state.memberAllergy,
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_MEMBER: (state, member) => (state.Member = member),
    SET_MEMBER_DISLIKE_INGREDIENT: (state, memberDislikeIngredient) => (state.memberDislikeIngredient = memberDislikeIngredient),
    GET_INGREDIENT: (state, ingredient) => (state.ingredient = ingredient),
    SET_MEMBER_ALLERGY: (state, memberAllergy) => (state.memberAllergy = memberAllergy)
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token);
      localStorage.setItem('token', token);
    },
    
    removeToken({ commit }) {
      commit('SET_TOKEN', '');
      localStorage.setItem('token', '');
    },

    // 유저 정보 가져오기
    fetchMember({ commit, getters, dispatch }, nickname) {
      if (!getters.isLoggedIn) {
        // 유저가 로그인되어 있지 않으면 아무 작업 없이 종료
        return;
      }
    
      // 유저 정보를 요청하여 Vuex 상태에 저장
      axios({
        url: api.member.member(nickname),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          if (res.data) {
            commit('SET__MEMBER', res.data)
            // localStorage.setItem('User', JSON.stringify(res.data))
            // 성공한 경우, 쿠키를 사용하거나 Vuex에만 저장하고 localStorage에 저장하지 않음
          } else {
            // 유저 정보가 없는 경우에 대한 처리 ex) 로그인 토큰은 있지만 유저 정보가 서버에 없는 경우
            dispatch('removeToken');
            router.push({ name: 'login' });
          }
        })
        .catch(err => {
          if (err.response) {
            // 서버에서 응답을 받았지만 응답이 에러인 경우에 대한 처리
            if (err.response.status === 401) {
              // 유효하지 않은 토큰 등 로그인 에러에 대한 처리
              dispatch('removeToken');
              router.push({ name: 'login' });
            } else if (err.response.status === 403) {
              // 권한이 없는 요청에 대한 처리 ex) 로그인은 했지만 해당 리소스에 접근할 권한이 없는 경우
              router.push({ name: 'forbidden' });
            } else {
              // 그 외 서버에서 받은 에러에 대한 처리
              console.error('서버 응답 에러:', err.response);
            }
          } else {
            // 네트워크 연결이 끊긴 경우
            // 서버에 요청 자체가 실패한 경우에 대한 처리
            console.error('서버 요청 실패:', err);
          }
        });
    },

    // 재료 리스트 가져오기
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

    // 유저 비선호 재료 생성
    createMemberDislikeIngredient({ commit, getters }, { nickname, memberDislikeIngredient }) {
      axios({
        url: api.member.memberDislikeIngredient(nickname),
        method: 'post',
        data: {
          memberDislikeIngredient,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    fetchMemberDislikeIngredient({ commit, getters }, { nickname }) {
      axios({
        url: api.member.memberDislikeIngredient(nickname),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    
    updateMemeberDislikeIngredient({ commit, getters  }, { nickname, memberDislikeIngredient }) {
      axios({
        url: api.member.memberDislikeIngredient(nickname),
        method: 'PUT',
        data: {
          memberDislikeIngredient
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    deleteMemberDislikeIngredient({ commit, getters }, { nickname }) {
      if(confirm('정말 초기화 하시겠습니까?')) {
        axios({
          url: api.member.memberDislikeIngredient(nickname),
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_MEMBER_DISLIKE_INGREDIENT', {});
          })
          .catch(err => {
            console.log(err.response);
          });
      }
    },

    fetchMemberAllergy({ commit, getters}, {nickname}) {
      axios ({
        url: api.member.memberAllergy(nickname),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_ALLERGY', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    }
  },
});

export default store;
