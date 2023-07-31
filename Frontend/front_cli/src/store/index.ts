import { createStore, Store } from 'vuex';
import member from './modules/member';
import recipe from './modules/recipe';
import atc from './modules/atc';

export interface RootState {
  // Root 상태 정의 (필요한 경우)
}

const store = createStore<RootState>({
  state: {

  },
  getters: {

  },
  mutations: {

  },
  actions: {

  },
  modules: {
    member,
    recipe,
    atc,
  },
});

export default store;
