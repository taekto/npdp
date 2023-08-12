import { Module } from 'vuex';
import { RootState } from '../index'; 
import router from '@/router';
import api from '@/api/api';
import axios from 'axios';
import { AxiosError } from 'axios';

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
  eamilCode: string | null;
  memberIngredient: MemberIngredient[];
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
    gender: string
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

// 회원 재료 
interface MemberIngredient {
  refregiratorId: number
  kor: string
  amount: number
  unit: any
  storage: number
  startDate: any
  expiredDate: any
}   

// 회원 재료 삭제
interface IngredientDeleteData {
  memberId: number
  refregiratorId: number
  storage: number
  amount: number
  unit: String
  startDate: Date
  expiredDate:  Date
  isdelete : boolean
}

// 회원 양념 삭제
interface SeasoningDeleteData {
  memberId: number;
  memberSeasoningId: number;
  storage: number;
  startDate: Date;
  expiredDate: Date;
  isdelete: boolean;
}

const member: Module<MemberState, RootState> = {
  state: {
    // 엑세스 토큰
    accessToken: null,
    // 회원 정보 조회 하면 일로 들어감
    member: [],
    // 로그인 시 입력한 이메일 저장
    currentMember: null,
    // 좋아하는 레시피 저장 할 용도
    memberRecipeLike: [  
      { member_recipe_like_id: 1, member_id: 1, recipe_id: 1 },
      { member_recipe_like_id: 2, member_id: 1, recipe_id: 2 },
      { member_recipe_like_id: 3, member_id: 1, recipe_id: 3 },
    ],
    // 
    memberRecipeLatest: [],
    memberDislikeIngredient: [],
    memberAllergy: [
      { member_allergy_id: 1, member_id: 1 , allergy_id: 3},
      { member_allergy_id: 2, member_id: 1 , allergy_id: 4},
      { member_allergy_id: 3, member_id: 1 , allergy_id: 5},
    ],
    memberUtensil: [],
    memberSeasoning: [],
    eamilCode: null,
    memberIngredient: [],
    
  },

  getters: {
    // 현재 회원 정보
    member: state => state.member,
    emailVerify: state => state.eamilCode,
    // 사용x
    currentMember: state => state.currentMember,
    isLoggedIn: () => !!sessionStorage.getItem('accessToken'),
    authHeader: () => ({ Authorization: `Bearer ${sessionStorage.getItem('accessToken')}` }),
    memberRecipeLike: state => state.memberRecipeLike,
    memberRecipeLatest: state => state.memberRecipeLatest,
    memberDislikeIngredient: state => state.memberDislikeIngredient,
    memberAllergy: state => state.memberAllergy,
    memberUtensilList: state => state.memberUtensil,
    memberSeasoning: state => state.memberSeasoning,
    memberIngredient: state => state.memberIngredient,
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
    SET_EMAIL_VERIFY: (state, emailCode) => (state.eamilCode = emailCode),
    SET_MEMBER_INGREDIENT: ( state, ingredient ) => (state.memberIngredient = ingredient),


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
          sessionStorage.setItem("accessToken", res.data.accessToken)
          sessionStorage.setItem("memberId", res.data.id)
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
    localSignup({ commit }, credentials) {
      console.log('회원가입 시작!', credentials)
        axios({
          url: 'https://i9b202.p.ssafy.io/api/members/join',
          method: 'post',
          data: credentials,
        })
        .then(res => {
          console.log('회원가입 성공!')
            console.log(res)
            alert('회원가입이 완료되었습니다!')
            router.push({ name: 'main' })
          })
          .catch(err => {
            ('회원가입 실패..')
            console.error(err.response.data)
            alert('회원가입에 실패하셨습니다.')
            // commit('SET_AUTH_ERROR', err.response.data)
          })
      },

      

    // 회원 이메일 인증
    async EmailVerify({commit}, email) {
      try {
        
        console.log(JSON.stringify({email}, null, 2))
        const response = await axios.post(api.member.emailVerify(), { email });
        console.log('이메일 전송 완료!', response.data);
     

        interface StoredData {
          value : string,
          timestamp : number
        }

        const data = {
          value: response.data,
          timestamp : new Date().getTime()
        }

        sessionStorage.setItem('emailVerify', JSON.stringify(data))

        const storedDataString = sessionStorage.getItem('emailVerify');
        const storedData: StoredData | null = storedDataString ? JSON.parse(storedDataString) : null;


        // const storedData = JSON.parse(sessionStorage.getItem('emailVerify'))
        if (storedData) {
          const currentTime = new Date().getTime();
          const storedTime = storedData.timestamp;
        
          // 현재 시간과 저장된 시간의 차이를 계산하고 3분(180000 밀리초)보다 작은지 확인
          if (currentTime - storedTime < 180000) {
            // 3분 이내에 저장된 데이터를 사용
            const value = storedData.value;
            console.log(value);
          } else {
            // 3분이 지난 데이터는 사용하지 않음
            sessionStorage.removeItem("emailVerify");
          }
        }

      } catch (error) {
        console.error(error);
      }
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
    fetchMember({ commit, getters}, memberId) {
      console.log('회원 정보 조회 시작!dasda', memberId)
      axios({
        url: `https://i9b202.p.ssafy.io/api/members/${memberId}`,
        method: 'get',
      })
        .then(res => {     
          console.log('회원 정보 조회 성공!', res.data) 
          commit('SET_MEMBER', res.data);
        })
        .catch(err => {
          console.log('회원 정보 조회 실패...', err)
        });
    },

    // 회원 재료 조회 양념/ 재료/ 전체
    async fetchMemberMaterial({ commit }, { type, memberId }) {
      try {
        if (type === 'all' || type === 'seasoning') {
          const seasoningApiUrl = `https://i9b202.p.ssafy.io/api/refregirator/seasoning/${memberId}`
          const seasoningResponse = await axios.get(seasoningApiUrl)
          console.log('양념 get 성공!', seasoningResponse.data)
          commit('SET_MEMBER_SEASONING', seasoningResponse.data)
        }
    
        if (type === 'all' || type === 'ingredient') {
          const ingredientApiUrl = `https://i9b202.p.ssafy.io/api/refregirator/ingredient/${memberId}`
          const ingredientResponse = await axios.get(ingredientApiUrl)
          console.log('재료 get 성공!', ingredientResponse.data)
          commit('SET_MEMBER_INGREDIENT', ingredientResponse.data)
        }
      } catch (error) {
        console.log('데이터 조회 실패..', error)
      }
    },

    // 회원 재료/양념/ 저장
    async saveMaterial({ dispatch }, {type, memberId, sendData}) {
      try {
        console.log(type === 'seasoning' ? '양념 저장 시작!': '재료 저장 시작!', sendData)
        const apiUrl = type === 'seasoning' ?
          `https://i9b202.p.ssafy.io/api/refregirator/member/seasoning/${memberId}`:
          `https://i9b202.p.ssafy.io/api/refregirator/member/ingredient/${memberId}` 
        console.log(JSON.stringify(sendData, null, 2))
        
        const response = await axios.post(apiUrl, sendData)
  
        console.log(type === 'seasoning' ? '양념 저장 성공!' : '재료 저장 성공!', response.data)
        dispatch('fetchIngredient')
      } catch (error) {
        console.log(type === 'seasoning' ? '양념 저장 실패..' : '재료 저장 실패..', error)
      }
    },

    // 회원 재료/양념 삭제
    async deleteMaterial({ dispatch }, { type, memberId, deleteItem }: { type: string, memberId: number, deleteItem: IngredientDeleteData | SeasoningDeleteData }) {
      try {
        let deleteData
        
        if (type === 'ingredient') {
          console.log('재료 삭제 시작!')
          const ingredientDeleteData = deleteItem as IngredientDeleteData
          deleteData = [{
            memberId: memberId,
            refregiratorId: ingredientDeleteData.refregiratorId,
            storage: ingredientDeleteData.storage,
            amount: ingredientDeleteData.amount,
            unit: ingredientDeleteData.unit,
            startDate: ingredientDeleteData.startDate,
            expiredDate: ingredientDeleteData.expiredDate,
            isdelete: true 
          }]
          
          console.log('재료 삭제 데이터:', JSON.stringify(deleteData, null, 2))
        } else if (type === 'seasoning') {
          console.log('양념 삭제 시작!')
          const seasoningDeleteData = deleteItem as SeasoningDeleteData
          deleteData = [{
            memberId: memberId,
            memberSeasoningId: seasoningDeleteData.memberSeasoningId,
            storage: seasoningDeleteData.storage,
            startDate: seasoningDeleteData.startDate,
            expiredDate: seasoningDeleteData.expiredDate,
            isdelete: true 
          }]
          console.log('양념 삭제 데이터:', JSON.stringify(deleteData, null, 2))
        }
        
        const apiUrl = type === 'seasoning' ?
        'https://i9b202.p.ssafy.io/api/refregirator/modify/seasoning':
        'https://i9b202.p.ssafy.io/api/refregirator/modify/ingredient';
    
        await axios.post(apiUrl, deleteData);

        await dispatch('fetchMemberMaterial', { type: 'all', memberId: memberId });

        console.log(type === 'seasoning' ? '양념 삭제 성공!' : '재료 삭제 성공!');
      } catch (err) {
        console.log(type === 'seasoning' ? '양념 삭제 실패..' : '재료 삭제 실패..', err);
      }
    },

    // 회원 레시피 좋아요/ 좋아요 취소
    async memberLikeRecipe({commit}, {type, memberId, recipeId}) {
      try {
        const apiUrl = 'https://i9b202.p.ssafy.io/api/members/heart'
        const sendData = { memberId, recipeId };
        
        console.log(type === 'like' ? `${memberId}님이 ${recipeId}를 좋아요 실행...` : `${memberId}님이 ${recipeId}를 취소 실행...` )

        const response = type === 'like' ?
        await axios.post(apiUrl, sendData) :
        await axios.delete(apiUrl, { data: sendData })
        
        console.log(type === 'like' ? `${memberId}님이 ${recipeId}를 좋아요 성공!` : `${memberId}님이 ${recipeId}를 취소 성공!` )

      } catch(err) {
        console.log(`${memberId}님이 ${recipeId}를 좋아요 실패...`, err)
      }
    },
    
    // 회원 레시피 좋아요 조회
    async fetchLike({commit}, memberId){
      try {
        console.log('좋아요 조회 시작!')
        const res = await axios.get(`https://i9b202.p.ssafy.io/api/members/heart/${memberId}`)
        console.log('좋아요 조회 성공!')
        commit('SET_MEMBER_RECIPE_LIKE', res.data)
      } catch(err) {
        console.log(err)
      }
    },

    // 회원 조리도구 조회/ 저장
    async memberUtensil({commit}, {type, memberId, utensilData}) {
      try {
        const apiUrl = type === 'get' ? `https://i9b202.p.ssafy.io/api/members/memberUtensil/${memberId}` : 'https://i9b202.p.ssafy.io/api/members/memberUtensil'
        if (type === 'post') {
          
          const sendData = {
            memberId: memberId,
            utensilId: utensilData // utensilData 배열을 그대로 사용합니다.
          };
          console.log(JSON.stringify(sendData, null, 2))
          
          const res = await axios.post(apiUrl, sendData);
          console.log('조리도구 저장 성공!:', res.data)
        
        } else {
          const res = await axios.get(apiUrl);
          console.log('조리도구 조회 성공!:', res.data);
          commit('SET_MEMBER_UTENSIL', res.data)
        }
      } catch (error) {
        console.error(type === 'get' ? '조리도구 조회 실패...' : '조리도구 저장 실패...', error);
      }
    },
  },
}

export default member;
