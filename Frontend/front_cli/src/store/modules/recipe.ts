import { createStore } from 'vuex'
import axios from 'axios'
import api from '@/api/api'



export default createStore({
  state: {
    token: localStorage.getItem('token') || '' ,
    ingredientList: [],
    userDisfavor: [],
  },
  getters: {
    userDisfavor: state => state.userDisfavor,
    ingredientList : state => state.ingredientList,
    authHeader: state => ({ Authorization: `Token ${state.token}`}),
  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_USERDISFAVOR: (state, userDisfavor) => state.userDisfavor = userDisfavor,
    SET_INGREDIENTLIST: (state, ingredientList) => state.ingredientList = ingredientList,
  },
  actions: {
    saveToken({ commit }, token) {

      commit('SET_TOKEN', token)
      localStorage.setItem('token', token,)
    },
                                //getters
    fetchIngredientList({ commit, }) { 
      axios ({
        url: api.ingredientsList(),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then (res => {
          commit('SET_INGREDIENTLIST', res.data)
        })
        .catch(err => console.log(err.response))
    },

                        //getters
    userDisfavor({ commit }, { userPk, ingredientId }) {
      axios({
        url: api.userDisfavor(userPk, ingredientId),
        method: 'post',
        // headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_USERDISFAVOR', res.data)
        })
        .catch(err => {
          console.log(err.response);
        });
    },
  },
  modules: {
  }
})
