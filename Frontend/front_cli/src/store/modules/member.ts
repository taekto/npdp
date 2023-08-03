import { Module } from 'vuex';
import { RootState } from '../index'; // Root Vuex Store에서 정의한 RootState 임포트
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';

interface MemberState {
  accessToken: string | null;
  // refreshToken: string | null;    
  member: Member[];
  memberRecipeLike: MemberRecipeLike[];
  memberRecipeLatest: MemberRecipeLatest[];
  memberDislikeIngredient: MemberDislikeIngredient[];
  memberAllergy: MemberAllergy[];
  memberUtensil: MemberUtensil[];
  memberSeasoning: MemberSeasoning[];
  currentMember: CurrentMember[];
}

// 회원
interface Member {
  email: string
  nickname: string
  birth: any
    // member_id: number
    // password: string
    // oauth: string
    // refresh_token: string
    // role: number
    // gender: string
    // quit: boolean
}

// 회원 현재 정보
interface CurrentMember {
  member_id: number
  email: string
  accessToken: string
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
  // have_id: number
  // member_id2: number
}

// 회원 알러지 정보
interface MemberAllergy {
  member_allergy_id: number
  member_id: number
  allergy_id: number
  // ingredient_id: number
}

// 회원 보유 장비
interface MemberUtensil {
  utensil_list_id: number
  member_id: number
  utensil_id: number
}

// 회원 양념칸
interface MemberSeasoning {
  seasoning_list_id : number
  seasoning_id: number
  member_id: number
  storage: number
}

const member: Module<MemberState, RootState> = {
  state: {
    accessToken: null,
    // refreshToken: null,
    member: [
      {
        // member_id: 1,
        email: 'user1@example.com',
        nickname: 'user1_nickname',
        // password: 'user1_password',
        // oauth: '',
        // role: 1,
        birth: '1990-01-01',
        // gender: 'male',
        // quit: false,
      },
      {
        // member_id: 2,
        email: 'user2@example.com',
        nickname: 'user2_nickname',
        birth: '1995-02-15',
        // password: 'user2_password',
        // oauth: '',
        // role: 2,
        // gender: 'female',
        // quit: true,
      },
      {
        // member_id: 3,
        email: 'user3@example.com',
        nickname: 'user3_nickname',
        birth: '1985-07-22',
        // password: 'user3_password',
        // oauth: 'google',
        // role: 1,
        // gender: 'female',
        // quit: false,
      },
      {
        // member_id: 4,
        email: 'user4@example.com',
        nickname: 'user4_nickname',
        // password: 'user4_password',
        // oauth: 'facebook',
        // role: 1,
        birth: '1992-11-11',
        // gender: 'male',
        // quit: false,
      },
      {
        // member_id: 5,
        email: 'user5@example.com',
        nickname: 'user5_nickname',
        birth: '1998-04-30',
        // password: 'user5_password',
        // oauth: '',
        // role: 2,
        // gender: 'female',
        // quit: true,
      },
    ],
    currentMember:[],
    memberRecipeLike: [  
      { member_recipe_like_id: 1, member_id: 1, recipe_id: 1 },
      { member_recipe_like_id: 2, member_id: 1, recipe_id: 2 },
      { member_recipe_like_id: 3, member_id: 1, recipe_id: 3 },
    ],

    memberRecipeLatest: [],
    memberDislikeIngredient: [],
    memberAllergy: [
      { member_allergy_id: 1, member_id: 1 , allergy_id: 3},
      { member_allergy_id: 2, member_id: 1 , allergy_id: 4},
      { member_allergy_id: 3, member_id: 1 , allergy_id: 5},
    ],
    memberUtensil: [],
    memberSeasoning: [],
  },

  getters: {
    member: state => state.member,
    currentMember: state => state.currentMember,
    isLoggedIn: () => !!sessionStorage.getItem('accessToken'),
    authHeader: state => ({ Authorization: `Bearer ${state.accessToken}` }),
    memberRecipeLike: state => state.memberRecipeLike,
    memberRecipeLatest: state => state.memberRecipeLatest,
    memberDislikeIngredient: state => state.memberDislikeIngredient,
    memberAllergy: state => state.memberAllergy,
    memberUtensil: state => state.memberUtensil,
    memberSeasoning: state => state.memberSeasoning
  },
  mutations: {
    SET_ACCESS_TOKEN: (state, accessToken) => (state.accessToken = accessToken),
    // SET_REFRESH_TOKEN: (state, refreshToken) => (state.refreshToken = refreshToken),
    SET_MEMBER: (state, member) => (state.member = member),
    SET_CURRENT_MEMBER: (state, currentMember) => (state.currentMember = currentMember),
    SET_MEMBER_RECIPE_LIKE: (state, memberRecipeLike) => (state.memberRecipeLike = memberRecipeLike),
    SET_MEMBER_RECIPE_LATEST: (state, memberRecipeLatest) => (state.memberRecipeLatest = memberRecipeLatest),
    SET_MEMBER_DISLIKE_INGREDIENT: (state, memberDislikeIngredient) => (state.memberDislikeIngredient = memberDislikeIngredient),
    SET_MEMBER_ALLERGY: (state, memberAllergy) => (state.memberAllergy = memberAllergy),
    SET_MEMBER_UTENSIL: (state, memberUtensil) => (state.memberUtensil = memberUtensil),
    SET_MEMBER_SEASONING: (state, memberSeasoning) => (state.memberSeasoning = memberSeasoning),
  },
  actions: {
    saveToken({ commit }, { accessToken}) {
      commit('SET_ACCESS_TOKEN', accessToken)
      localStorage.setItem('accessToken', accessToken)
      // commit('SET_REFRESH_TOKEN', refreshToken)
      // localStorage.setItem('refreshToken', refreshToken)
    },

    removeToken({ commit }) {
      commit('SET_ACCESS_TOKEN', null)
      localStorage.removeItem('accessToken')
      // commit('SET_REFRESH_TOKEN', null)
      // localStorage.removeItem('refreshToken')
    },

    localLogin({ dispatch }, credentials) {
      console.log(credentials)
      axios({
        url: api.member.login(),
        method: 'post',
        data: credentials,
      })
        .then(res => {
          console.log('로컬로그인')
          console.log(res)
          sessionStorage.setItem("accessToken", res.data.accessToken);
          // commit('SET_CURRENT_MEMBER', res.data)
          dispatch('fetchMember', res.data.memeberId)
          router.push({ name: 'main' })
        })
        .catch(err => {
          console.error(err.response.data)
        })
    },

    // 로컬 회원 가입
    localSignup({ commit }, credentials) {
      console.log(credentials)
        axios({
          url: api.member.signup(),
          method: 'post',
          data: credentials,
        })
        .then(res => {
          console.log('-----------------------------------------------')
            console.log(res)
            // const token = res.data.token
            // dispatch('saveToken', token)
            commit('SET_CURRENT_MEMBER', res.data)
            // dispatch('fetchMember', res.data.member_id)
            alert('회원가입이 완료되었습니다!')
            router.push({ name: 'main' })
          })
          .catch(err => {
            console.error(err.response.data)
            // commit('SET_AUTH_ERROR', err.response.data)
          })
      },

    // 회원 로그아웃
    logout({  dispatch, commit }) {
      dispatch('removeToken')
      commit('SET_CUREENT_MEMBER', null)
      alert('성공적으로 logout!')
      router.push({ name: 'login' })
    },

    // 회원 정보 조회
    fetchMember({ commit, getters}, member_id) {
      if (!getters.currentMember) {
        // 로그인 리턴값이 없으면 아무것도 안하고 종료
        return
      }
      // 유저 정보를 요청하여 Vuex 상태에 저장
      axios({
        url: api.member.member(member_id),
        method: 'get',
      })
        .then(res => {     
          console.log(res.data) 
          console.log('회원 정보 조회 (fetchMember)') 
          commit('SET_MEMBER', res.data);
        })
        .catch(err => {
          console.log(err.response)
          router.push({ name: 'login' })
        })
    },

    // 회원 레시피 좋아요
    memberLikeRecipe ({ commit, getters}, {member_id, recipe_id}) {
      axios ({
        url: api.member.memberRecipeLike(member_id),
        method: 'post',
        data: {
          recipe_id
        },
        headers: getters.authHeader,
      })
        .then (res=> {
          commit('SET_MEMBER_RECIPE_LIKE', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },


    // 회원 최근 본 레시피
    // memberRecipeLatest (생성, 조회, 수정, 삭제)

    createMemberRecipeLatest({ commit, getters }, {member_id, recipe_id, date}) {
      axios({
        url: api.member.memberRecipeLatest(member_id),
        method: 'post',
        data: {
          recipe_id,
          date,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          const latestRecipes: MemberRecipeLatest[] = getters.memberRecipeLatest
          const existingIndex = latestRecipes.findIndex((item: MemberRecipeLatest) => item.recipe_id === res.data.recipe_id)
          
          if (existingIndex !== -1) {
            // 이미 해당 레시피가 최근 본 레시피 목록에 존재하는 경우, 해당 레시피를 삭제
            latestRecipes.splice(existingIndex, 1)
          } else if (latestRecipes.length >= 20) {
            // 최근 본 레시피 목록이 꽉 찬 경우 가장 오래된 레시피를 삭제
            latestRecipes.pop()
          }
    
          // 새로운 레시피를 맨 앞에 추가
          latestRecipes.unshift(res.data)
          commit('SET_MEMBER_RECIPE_LATEST', latestRecipes)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 조회
    fetchMemberRecipeLatest({ commit, getters }, member_id) {
      axios({
        url: api.member.memberRecipeLatest(member_id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_RECIPE_LATEST', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },


    // 유저 비선호 재료 생성/ 조회/ 수정
    createMemberDislikeIngredient({ commit, getters }, { member_id, ingredient_id }) {
      axios({
        url: api.member.memberDislikeIngredient(member_id),
        method: 'post',
        data: {
          ingredient_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    fetchMemberDislikeIngredient({ commit, getters }, { member_id }) {
      axios({
        url: api.member.memberDislikeIngredient(member_id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    updateMemeberDislikeIngredient({ commit, getters  }, { member_id, ingredient_id }) {
      axios({
        url: api.member.memberDislikeIngredient(member_id),
        method: 'PUT',
        data: {
          ingredient_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    deleteMemberDislikeIngredient({ commit, getters }, { member_id }) {
      if(confirm('정말 초기화 하시겠습니까?')) {
        axios({
          url: api.member.memberDislikeIngredient(member_id),
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_MEMBER_DISLIKE_INGREDIENT', {})
          })
          .catch(err => {
            console.log(err.response)
          })
      }
    },
        
    // memberAllergy (생성, 조회, 수정, 삭제)
    createMemberAllergy({ commit, getters }, {member_id, allergy_id}) {
      axios({
        url: api.member.memberAllergy(member_id),
        method: 'post',
        data: {
          allergy_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_ALLERGY', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    fetchMemberAllergy({ commit, getters }, member_id) {
      axios({
        url: api.member.memberAllergy(member_id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_ALLERGY', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    updateMemberAllergy({ commit, getters }, {member_id, allergy_id}) {
      axios({
        url: api.member.memberAllergy(member_id),
        method: 'put',
        data:  {
          allergy_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_ALLERGY', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    // 삭제
    deleteMemberAllergy({ commit, getters }, member_id) {
      axios({
        url: api.member.memberAllergy(member_id),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          commit('SET_MEMBER_ALLERGY', {})
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    // memberUtensil (생성, 조회, 수정, 삭제)
    createMemberUtensil({ commit, getters }, {member_id, utensil_id}) {
      axios({
        url: api.member.memberUtensil(member_id),
        method: 'post',
        data: {
          utensil_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_UTENSIL', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 조회
    fetchMemberUtensil({ commit, getters }, member_id) {
      axios({
        url: api.member.memberUtensil(member_id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_UTENSIL', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 수정 (특정 보유 장비 정보 업데이트)
    updateMemberUtensil({ commit, getters }, {member_id, utensil_id}) {
      axios({
        url: api.member.memberUtensil(member_id),
        method: 'put',
        data: {
          utensil_id
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_UTENSIL', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 삭제
    deleteMemberUtensil({ commit, getters }, {member_id}) {
      axios({
        url: api.member.memberUtensil(member_id),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          commit('SET_MEMBER_UTENSIL', {})
        })
        .catch(err => {
          console.log(err.response)
        })
    },

    // memberSeasoning (생성, 조회, 수정, 삭제)
    createMemberSeasoning({ commit, getters }, {member_id, seasoning_id}) {
      axios({
        url: api.member.memberTextSeasoning(member_id),
        method: 'post',
        data: {
          seasoning_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_SEASONING', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 회원 양념 조회
    fetchMemberSeasoning({ commit, getters }, member_id) {
      axios({
        url: api.member.fetchMemberSeasoning(member_id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_SEASONING', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 회원 양념 수정
    updateMemberSeasoning({ commit, getters }, {member_id, seasoning_id}) {
      axios({
        url: api.member.fetchMemberSeasoning(member_id),
        method: 'put',
        data: {
          seasoning_id,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_MEMBER_SEASONING', res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    
    // 회원 양념 삭제
    deleteMemberSeasoning({ commit, getters }, member_id) {
      axios({
        url: api.member.deleteMemberSeasoning(member_id),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          commit('SET_MEMBER_SEASONING', {})
        })
        .catch(err => {
          console.log(err.response)
        })
    },
  },
}

export default member;
