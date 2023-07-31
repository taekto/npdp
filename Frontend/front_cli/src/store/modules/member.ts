import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';

interface MemberState {
  accessToken: string | null;
  refreshToken: string | null;    
  member: Member[];
  memberRecipeLike: MemberRecipeLike[];
  memberRecipeLatest: MemberRecipeLatest[];
  memberDislikeIngredient: MemberDislikeIngredient[];
  memberAllergy: MemberAllergy[];
  memberUtensil: memberUtensil[];
  memberSeasoning: memberSeasoning[];
}

// 회원
interface Member {
    member_id: number
    email: string
    nickname: string
    password: string
    oauth: string
    // refresh_token: string
    role: number
    birth: string
    gender: string
    quit: boolean
}
// 회원 레시피 좋아요
interface MemberRecipeLike {
  member_recipe_like_id : number
  member_id: number
  recipe_id: number
}

// 회원 최근 본 레시피
interface MemberRecipeLatest {
  member_recipe_latest_id: number
  member_id: number
  recipe_id: number
  date: Date
}

// 회원 비선호 재료
interface MemberDislikeIngredient {
  member_dislike_ingredient_id:number
  member_id: number
  ingredient_id: number
  have_id: number
  member_id2: number
}

// 회원 알러지 정보
interface MemberAllergy {
  have_id: number
  member_id: number
  // ingredient_id: number
}

// 회원 보유 장비
interface memberUtensil {
  utensil_list_id: number
  member_id: number
  utensil_id: number
}

// 양념칸
interface memberSeasoning {
  seasoning_list_id : number
  seasoning_id: number
  member_id: number
  storage: number
}

const member: Module<MemberState, RootState> = {
  state: {
    accessToken: null,
    refreshToken: null,
    member: [],
    memberRecipeLike: [],
    memberRecipeLatest: [],
    memberDislikeIngredient: [],
    memberAllergy: [
      { have_id: 1, member_id: 1 },
      { have_id: 2, member_id: 1 },
      { have_id: 3, member_id: 1 },
    ],
    memberUtensil: [],
    memberSeasoning: [],
  },

  getters: {
    member: state => state.member,
    isLoggedIn: state => !!state.accessToken,
    authHeader: state => ({ Authorization: `Bearer ${state.accessToken}` }),
    memberRecipeLike: state => state.memberRecipeLike,
    memberRecipeLatest: state => state.memberRecipeLatest,
    memberDislikeIngredient: state => state.memberDislikeIngredient,
    memberAllergy: state => state.memberAllergy,
    memberUtensil: state => state.memberUtensil,
    memberSeasoning: state => state.memberSeasoning
  },
  mutations: {
    SET_ACESS_TOKEN: (state, accessToken) => (state.accessToken = accessToken),
    SET_REFRESH_TOKEN: (state, refreshToken) => (state.refreshToken = refreshToken),
    SET_MEMBER: (state, member) => (state.member = member),
    SET_MEMBER_RECIPE_LIKE: (state, memberRecipeLike) => (state.memberRecipeLike = memberRecipeLike),
    SET_MEMBER_RECIPE_LATEST: (state, memberRecipeLatest) => (state.memberRecipeLatest = memberRecipeLatest),
    SET_MEMBER_DISLIKE_INGREDIENT: (state, memberDislikeIngredient) => (state.memberDislikeIngredient = memberDislikeIngredient),
    SET_MEMBER_ALLERGY: (state, memberAllergy) => (state.memberAllergy = memberAllergy),
    SET_MEMBER_UTENSIL: (state, memberUtensil) => (state.memberUtensil = memberUtensil),
    SET_MEMBER_SEASONING: (state, memberSeasoning) => (state.memberSeasoning = memberSeasoning),
  },
  actions: {
    saveTokens({ commit }, { accessToken, refreshToken }) {
      commit('SET_ACCESS_TOKEN', accessToken);
      commit('SET_REFRESH_TOKEN', refreshToken);
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
    },

    removeTokens({ commit }) {
      commit('SET_ACCESS_TOKEN', null);
      commit('SET_REFRESH_TOKEN', null);
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
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
            commit('SET_MEMBER', res.data)
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
};

export default member;
