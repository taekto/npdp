import { Module } from 'vuex';
import { RootState } from '../index'; 
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
  currentMember: string | null;
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
    member: [],
    currentMember: null,
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
    // 현재 회원 정보
    member: state => state.member,
    // 사용x
    currentMember: state => state.currentMember,
    isLoggedIn: () => !!sessionStorage.getItem('accessToken'),
    authHeader: () => ({ Authorization: `Bearer ${sessionStorage.getItem('accessToken')}` }),
    memberRecipeLike: state => state.memberRecipeLike,
    memberRecipeLatest: state => state.memberRecipeLatest,
    memberDislikeIngredient: state => state.memberDislikeIngredient,
    memberAllergy: state => state.memberAllergy,
    memberUtensil: state => state.memberUtensil,
    memberSeasoning: state => state.memberSeasoning
  },
  mutations: {
    // SET_ACCESS_TOKEN: (state, accessToken) => (state.accessToken = accessToken),
    // SET_REFRESH_TOKEN: (state, refreshToken) => (state.refreshToken = refreshToken),
    SET_MEMBER: (state, member) => (state.member = member),
    SET_CURRENT_MEMBER: (state, email) => (state.currentMember = email),
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
    },

    removeToken() {
      sessionStorage.removeItem("accessToken")
    },

    // 회원 로그인
    localLogin({ dispatch, commit }, credentials) {
      console.log(credentials)
      axios({
        url: api.member.login(),
        method: 'post',
        data: credentials,
      })
        .then(res => {
          console.log('로컬로그인 시작!')
          console.log(res)
          sessionStorage.setItem("accessToken", res.data.accessToken);
          commit('SET_CURRENT_MEMBER', credentials.email)
          // commit('SET_CURRENT_MEMBER', res.data)
          dispatch('fetchMember', res.data.id)
          router.push({ name: 'main' })
          setTimeout(() => {
            router.go(0)
          }, 500)
        })
        .catch(err => {
          console.log('로그인 실패..')
          console.error(err.response.data)
        })
    },

    // 로컬 회원 가입
    localSignup(credentials) {
      console.log('회원가입 시작!')
        axios({
          url: api.member.signup(),
          method: 'post',
          data: credentials,
        })
        .then(res => {
          console.log('회원가입 성공!')
            console.log(res)
            // const token = res.data.token
            // dispatch('saveToken', token)
            // dispatch('fetchMember', res.data.member_id)
            alert('회원가입이 완료되었습니다!')
            router.push({ name: 'main' })
          })
          .catch(err => {
            ('회원가입 실패..')
            console.error(err.response.data)
            // commit('SET_AUTH_ERROR', err.response.data)
          })
      },

    // 회원 로그아웃
    logout({ dispatch, commit }) {
      dispatch('removeToken')
      commit('SET_MEMBER', null)
      alert('성공적으로 logout!')
      router.push({ name: 'main' })
      setTimeout(() => {
        router.go(0)
      }, 500)
    },

    // 회원 정보 조회
    fetchMember({ commit, getters}, member_id) {
      if (!getters.currentMember) {
        // 로그인 리턴값이 없으면 아무것도 안하고 종료
        return
      }
      axios({
        url: api.member.member(member_id),
        method: 'get',
      })
        .then(res => {     
          console.log(res.data) 
          console.log('회원 정보 조회 성공! (fetchMember)') 
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
