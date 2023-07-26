import { createStore } from 'vuex'
import axios from 'axios'
import api from '@/api/api'
import router from '@/router'

interface State {
  token: string
  ingredientList: Ingredient[]
  userDisfavorList: DisfavorIngredient[]
  currentUser: Member[]
}

interface Ingredient {
  ingredient_id: number
  name: string
}

interface DisfavorIngredient {
  member_dislike_ingredient_id:number
  member_id: number
  ingredient_name: number
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

const store = createStore<State>({
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: [],
    ingredientList: [
      // 임시 데이터 ( 삭제해야함 )
      { ingredient_id: 1, name: '재료 1' },
      { ingredient_id: 2, name: '재료 2' },
      { ingredient_id: 3, name: '재료 3' },
      { ingredient_id: 4, name: '치킨' },
      { ingredient_id: 5, name: '꽁치' },
      { ingredient_id: 6, name: '통조림' },
      { ingredient_id: 7, name: '담배' },
      { ingredient_id: 8, name: '술' },
      { ingredient_id: 9, name: '재리' },
    ],
    userDisfavorList: [],
  },
  getters: {
    userDisfavorList: state => state.userDisfavorList,
    ingredientList: state => state.ingredientList,
    authHeader: state => ({ Authorization: `Token ${state.token}` }),
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_USERDISFAVORLIST: (state, userDisfavorList) => (state.userDisfavorList = userDisfavorList),
    GET_INGREDIENTLIST: (state, ingredientList) => (state.ingredientList = ingredientList),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
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
    fetchCurrentUser({ commit, getters, dispatch }) {
      /*
      GET: 사용자가 로그인 했다면(토큰이 있다면)
        currentUserInfo URL로 요청보내기
          성공하면
            state.cuurentUser에 저장
          실패하면(토큰이 잘못되었다면)
            기존 토큰 삭제
            LoginView로 이동
      */
      if (getters.isLoggedIn) {
        axios({
          url: api.user.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader,
        })
          .then(res => {
            commit('SET_CURRENT_USER', res.data)
            localStorage.setItem('currentUser', JSON.stringify(res.data))
          })
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('removeToken')
              router.push({ name: 'login' })
            }
          })
      }
    },
    // 재료 리스트 가져오기
    fetchIngredientList({ commit, getters }) {
      axios({
        url: api.recipe.ingredientList(),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('GET_INGREDIENTLIST', res.data);
        })
        .catch(err => console.log(err.response));
    },

    // 유저 비선호 재료 생성
    createUserDisfavorList({ commit, getters }, { userPk, userSelectDisfavorList }) {
      axios({
        url: api.user.userDisfavorList(userPk),
        method: 'post',
        data: {
          userSelectDisfavorList,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_USERDISFAVORLIST', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    fetchUserDisfavorList({ commit, getters }, { userPk }) {
      axios({
        url: api.user.userDisfavorList(userPk),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_USERDISFAVORLIST', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    
    updateUserDisfavorList({ commit, getters  }, { userPk, userSelectDisfavorList }) {
      axios({
        url: api.user.userDisfavorList(userPk),
        method: 'PUT',
        data: {
          userPk,
        },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_USERDISFAVORLIST', res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    deleteUserDisfavorList({ commit, getters }, { userPk }) {
      if(confirm('정말 초기화 하시겠습니까?')) {
        axios({
          url: api.user.userDisfavorList(userPk),
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_USERDISFAVORLIST', {});
          })
          .catch(err => {
            console.log(err.response);
          });
      }
    },
    
  },
});

export default store;
