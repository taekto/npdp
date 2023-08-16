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
  isRecipeLike: boolean;
  memberSimilarity:MemberSimilarity[];
  allergyList: AllergyList[];
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
  recipeId: number
  name: string
  imgBig: string
  imgSmall: string
  category: string
}

// 회원 최근 본 레시피
interface MemberRecipeLatest {
  recipe_id: number
  name: string
  imgBig: string
  imgSmall: string
  category: string
  date: Date
}

// 회원 비선호 재료
interface MemberDislikeIngredient {
  ingredientId: number,
  ingredientName : String
}

// 회원 알러지 정보
interface MemberAllergy {
  allergyId: number
  allergyName: string
}

// 회원 알러지 리스트
interface AllergyList {
  allergyId: number
  allergyName: string
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

// 회원 재료 수정/삭제
interface ingredientUpdateData {
  memberId: number
  refregiratorId: number
  storage: number
  amount: number
  unit: String
  startDate: Date
  expiredDate:  Date
  isdelete : boolean
}

// 회원 양념 수정/삭제
interface SeasoningUpdateData {
  memberId: number;
  memberSeasoningId: number;
  storage: number;
  startDate: Date;
  expiredDate: Date;
  isdelete: boolean;
}

// 유저 레시피 추천
interface MemberSimilarity {
  recipeId: number
  name: string
  imgBig: string
  imgSmall: string
  category: string
  similarity: number
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
    memberRecipeLike: [],
    // 
    memberRecipeLatest: [],
    memberDislikeIngredient: [],
    memberAllergy: [],
    memberUtensil: [],
    memberSeasoning: [],
    eamilCode: null,
    memberIngredient: [],
    isRecipeLike: false,
    memberSimilarity:[],
    allergyList:[],
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
    memberTypeIds: (state) =>  (type: string) => { if (type ==='dislike') { return state.memberDislikeIngredient.map(item => item.ingredientId)} else if (type === 'allergy') {return state.memberAllergy.map(item => item.allergyId)}},
    isRecipeLike: (state) => state.isRecipeLike,
    memberSimilarity: (state) => state.memberSimilarity,
    allergyList:(state) => state.allergyList,
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
    // 기존 배열에 추가(기존 SET_MEMBER_DISLIKE_INGREDIENT에 push할경우 저장을 누르면 기존배열 + 업데이트 배열)
    UPDATE_LIST: (state, { type, updateData }) => {if (type === 'dislike') {state.memberDislikeIngredient.push(...updateData)} else if (type === 'allergy') {state.memberAllergy.push(...updateData)}},
    REMOVE_FROM_LIST: (state, { type, removeData }) => {if (type === 'dislike') {state.memberDislikeIngredient = state.memberDislikeIngredient.filter(item => item.ingredientId !== removeData)} else if (type === 'allergy') {state.memberAllergy = state.memberAllergy.filter(item => item.allergyId !== removeData)}},
    SET_IS_RECIPE_LIKE: (state, isLike) => (state.isRecipeLike = isLike),
    SET_MEMBER_SIMILARITY: (state, similarityData) => (state.memberSimilarity = similarityData),
    SET_ALLERGY_LIST:(state, allergyList) => (state.allergyList = allergyList),
  },
  actions: {
    saveToken({ commit }, { accessToken}) {
      commit('SET_ACCESS_TOKEN', accessToken)
      localStorage.setItem('accessToken', accessToken)
    },

    removeToken() {
      sessionStorage.removeItem("accessToken")
      sessionStorage.removeItem("memberId")
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
          sessionStorage.setItem("email", credentials.email)
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
          alert("로그인에 실패하셨습니다.")
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
        alert('임시비밀번호를 발급하였습니다.')
     

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
        })
    },

    // 회원 재료 조회 양념/ 재료/ 전체
    async fetchMemberMaterial({ commit }, { type, memberId }) {
      try {
        if (type === 'all' || type === 'seasoning' || type === 'seasoningDelete') {
          const seasoningApiUrl = `https://i9b202.p.ssafy.io/api/refregirator/seasoning/${memberId}`
          const seasoningResponse = await axios.get(seasoningApiUrl)
          console.log('양념 get 성공!', seasoningResponse.data)
          commit('SET_MEMBER_SEASONING', seasoningResponse.data)
        }
    
        if (type === 'all' || type === 'ingredient' || type === 'ingredientDelete') {
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
        console.log(type === 'seasoning' ? '양념 저장 시작!': '재료 저장 시작!',JSON.stringify(sendData, null, 2))
        const apiUrl = type === 'seasoning' ?
          `https://i9b202.p.ssafy.io/api/refregirator/member/seasoning/${memberId}`:
          `https://i9b202.p.ssafy.io/api/refregirator/member/ingredient/${memberId}` 
        
        const response = await axios.post(apiUrl, sendData)
  
        console.log(type === 'seasoning' ? '양념 저장 성공!' : '재료 저장 성공!', response.data)
        dispatch('fetchMemberMaterial', ({type:type, memberId:memberId}))
      } catch (error) {
        console.log(type === 'seasoning' ? '양념 저장 실패..' : '재료 저장 실패..', error)
      }
    },


    // 회원 재료/양념 수정, 삭제
    async updateMaterial({ dispatch }, { type, memberId, updateItem }: { type: string, memberId: number, updateItem: ingredientUpdateData | SeasoningUpdateData }) {
      try {
        let updateData

        if (type === 'ingredient') {
          console.log('재료 수정/삭제 시작!')
          const ingredientUpdateData = updateItem as ingredientUpdateData
          updateData = [{
            memberId: memberId,
            refregiratorId: ingredientUpdateData.refregiratorId,
            storage: ingredientUpdateData.storage,
            amount: ingredientUpdateData.amount,
            unit: ingredientUpdateData.unit,
            startDate: ingredientUpdateData.startDate,
            expiredDate: ingredientUpdateData.expiredDate,
            isdelete: ingredientUpdateData.isdelete 
          }]
          
          console.log('재료수정 데이터:', JSON.stringify(updateData, null, 2))
        } else if (type === 'seasoning') {
          console.log('양념 수정/삭제 시작!')
          const seasoningUpdateData = updateItem as SeasoningUpdateData
          updateData = [{
            memberId: memberId,
            memberSeasoningId: seasoningUpdateData.memberSeasoningId,
            storage: seasoningUpdateData.storage,
            startDate: seasoningUpdateData.startDate,
            expiredDate: seasoningUpdateData.expiredDate,
            isdelete: seasoningUpdateData.isdelete 
          }]
          console.log('양념 수정 데이터:', JSON.stringify(updateData, null, 2))
        } else if (type === 'ingredientDelete') {
          console.log('재료 수정/삭제 시작!')
          const ingredientUpdateData = updateItem as ingredientUpdateData
          updateData = [{
            memberId: memberId,
            refregiratorId: ingredientUpdateData.refregiratorId,
            storage: ingredientUpdateData.storage,
            amount: ingredientUpdateData.amount,
            unit: ingredientUpdateData.unit,
            startDate: ingredientUpdateData.startDate,
            expiredDate: ingredientUpdateData.expiredDate,
            isdelete: true,
          }]
        } else if (type === 'seasoningDelete') {
          console.log('양념 수정/삭제 시작!')
          const seasoningUpdateData = updateItem as SeasoningUpdateData
          updateData = [{
            memberId: memberId,
            memberSeasoningId: seasoningUpdateData.memberSeasoningId,
            storage: seasoningUpdateData.storage,
            startDate: seasoningUpdateData.startDate,
            expiredDate: seasoningUpdateData.expiredDate,
            isdelete: true,
          }]
        }
        
        const apiUrl = type === 'seasoning' ?
        'https://i9b202.p.ssafy.io/api/refregirator/modify/seasoning':
        'https://i9b202.p.ssafy.io/api/refregirator/modify/ingredient'
    
        const res = await axios.post(apiUrl, updateData)

        dispatch('fetchMemberMaterial', {type:type, memberId: memberId})
        
        console.log('뭔가 성공...!')

      } catch (err) {
        console.log(err)
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
        
        if (type === 'like') {
          commit('SET_IS_RECIPE_LIKE', true);
        } else {
          commit('SET_IS_RECIPE_LIKE', false);
        }

        
      } catch(err) {
        console.log(`${memberId}님이 ${recipeId}를 좋아요 실패...`, err)
      }
    },
 
    // 회원 레시피 좋아요 조회
    async fetchLike({commit}, memberId){
      try {
        console.log('좋아요 조회 시작!')
        const res = await axios.get(`https://i9b202.p.ssafy.io/api/members/heart/${memberId}`)
        console.log('좋아요 조회 성공!', res.data)
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
          }
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
    
    // 회원 정보 변경
    async memberUpdate({commit}, {type, memberId, updateData}) {
      try {
        let sendData

        const apiUrl = type === 'all' ? 'https://i9b202.p.ssafy.io/api/members/modifyAll' : 'https://i9b202.p.ssafy.io/api/members/updatePassword'
        if (type == 'all') {
          const allInfo = updateData 
          sendData = {
            memberId: memberId,
            password:allInfo.password,
            nickname: allInfo.nickname,
            birth: allInfo.birthDate,
            gender: allInfo.gender,
          } 
        } else {
          const pwdInfo =  updateData
          sendData = {
            email: pwdInfo.email,
            newPassword: pwdInfo.newPassword,     
          }
        }

        console.log('회원정보 수정 시작!',JSON.stringify(sendData,null,2))
        const res = await axios.put(apiUrl, sendData);
        

      } catch(err) {
        console.error(type === 'all' ? '정보 변경 실패...' : '비밀번호 변경 실패...', err)
      }
    },

    // 회원 비선호 재료, 알러지 저장/ 조회
    async memberDislikeAllergy({commit, getters}, {type, memberId}) {
      try {
        let apiUrl
        let sendData
        
        if (type === 'dislikeGet') {
          apiUrl = `https://i9b202.p.ssafy.io/api/members/dislikeIngredient/${memberId}`         
        } else if (type === 'dislikePost') {
          apiUrl = 'https://i9b202.p.ssafy.io/api/members/dislikeIngredient'  
          sendData = {
            memberId: memberId,
            ingredientId: getters.memberTypeIds('dislike')
          }       
        } else if (type === 'allergyGet') {
          apiUrl = `https://i9b202.p.ssafy.io/api/members/memberAllergy/${memberId}`
        } else if (type === 'allergyPost') {
          apiUrl = 'https://i9b202.p.ssafy.io/api/api/members/memberAllergy'
          sendData = {
            memberId: memberId,
            allergyId: getters.memberTypeIds('allergy')
          }
        } else {
          throw new Error('Invalid request type');
        }
        
        console.log(
          type === 'dislikeGet' || type === 'dislikePost'
            ? '비선호 재료 요청 시작!'
            : '알러지 재료 요청 시작!',
            sendData
        )

        
        let res
        if (type === 'dislikeGet' || type === 'allergyGet') {
          res = await axios.get(apiUrl)
        } else if (type === 'dislikePost' || type === 'allergyPost') {
          res = await axios.post(apiUrl, sendData)
        }
        
        
        console.log(
          type === 'dislikeGet' || type === 'dislikePost'
          ? '비선호 재료 요청 성공!'
          : '알러지 재료 요청 성공!',
          res
          )
          
          if (type === 'dislikeGet') {
            if (res && res.data) {
              commit('SET_MEMBER_DISLIKE_INGREDIENT', res.data);
            }
          } else if (type === 'dislikePost') {
            console.log('비선호 재료 저장 완료!')
          } else if (type === 'allergenGet') {
            if (res && res.data) {
              commit('SET_MEMBER_ALLERGY', res.data);
            }
          } else if (type === 'allergenPost') {
            if (res && res.data) {
              console.log('알러지 저장 완료!')
            }
          }
      } catch(err) {
        console.log(
          type === 'dislikeGet' || type === 'dislikePost'
            ? '비선호 재료 요청 실패..'
            : '알러지 재료 요청 실패..',
          err
        )
      }
    },
    
    // 회원 알러지 리스트 조회
    async fetchAllergyList({commit}) {
      try{
        console.log('알러지 리스트 조회 시작!')
        const res = await axios.get('https://i9b202.p.ssafy.io/api/allergy')
        console.log('알러지 리스트 조회 성공!', res.data)
        commit('SET_ALLERGY_LIST', res.data)

      }catch(err) {
        console.log(err)
      }
    },

    // 아이템 추가 (비선호, 알러지)
    async appendItem({ commit, state }, {type, inputData}) {
      try {
        let data = {}
        let isDuplicate = false
        if (type === 'dislike') {
          data = { ingredientId: inputData.id, ingredientName: inputData.name }
          isDuplicate = state.memberDislikeIngredient.some(item => item.ingredientId === inputData.id)
        } else if (type === 'allergy') {
          data = { allergyId: inputData.allergyId, allergyName: inputData.allergyName }
          isDuplicate = state.memberAllergy.some(item => item.allergyId === inputData.allergyId)
        }

        if (!isDuplicate) {
          commit('UPDATE_LIST', { type: type, updateData: [data] })
        } else {
          console.log('이미 존재하는 재료 또는 알러지입니다.',)
        }
      } catch(err) {
        console.log(err)
      }
    },
    
    // 아이템 삭제
    async deleteItem({commit}, {type, delData}){
      try {
        commit('REMOVE_FROM_LIST', { type, removeData: delData })
        console.log(delData,'삭제성공!')
      } catch(err) {
        console.log(delData,'삭제실패,,,,',err)
      }
    },

    // 회원 최근 본 레시피
    async latestRecipe({commit}, {type, memberId, lookData}) {
      try{  
        console.log('회원 최근 본 레시피 조회/저장!')
        const apiUrl = 'https://i9b202.p.ssafy.io/api/members/latest'
        
        if (type === 'post') {
          const res = await axios.post('https://i9b202.p.ssafy.io/api/members/latest', lookData)
          console.log('최근 본 레시피 저장...')
        } else {
          const res = await axios.get(`https://i9b202.p.ssafy.io/api/members/latest/${memberId}`)

          console.log('최근 본 레시피 조회 성공',res.data)

          commit('SET_MEMBER_RECIPE_LATEST', res.data)
        }
        
      } catch(err) {
        console.log('최근 본 레시피 조회 실패...', err)
      }
    },

    // 회원 기반 레시피 추천
    async memberRecommend({commit}, memberId) {
      try{
        console.log('유저 기반 레시피 추천 시작!')
        const data = {memberId: memberId}
        const res = await axios.post('https://i9b202.p.ssafy.io/api/recipes/member/similarity', data)
        console.log('유저 기반 레시피 추천 성공!', res.data)
        commit('SET_MEMBER_SIMILARITY', res.data)
      }catch(err) {
        console.log(err)
      }
    }

  }, 
}

export default member
