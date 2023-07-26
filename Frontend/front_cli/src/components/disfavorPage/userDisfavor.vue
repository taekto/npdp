<template>
  <div>
    <form @submit.prevent="addDisfavorIngredient">
      <div class="input-group">
        <input v-model="searchKeyword" class="form-control" type="text">
        <input type="submit" value="검색">
      </div>
    </form>

    <div>
      <h3>검색 결과</h3>
      <ul>
        <li v-for="ingredient in searchResults" :key="ingredient.id">
          <span>{{ ingredient.name }}</span>
          <button @click="userDisfavor(ingredient.id)">추가</button>
        </li>
      </ul>
    </div>

    <div>
      <h3>비선호 재료 리스트</h3>
      <ul>
        <li v-for="disfavorIngredient in disfavorIngredients" :key="disfavorIngredient.id">
          {{ fetchIngredientList.name }}
        </li>
      </ul>
    </div>

    <button @click="userDisfavor">저장</button>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  data() {
    return {
      searchKeyword: '',
    };
  },
  computed: {
    ...mapGetters(['userDisfavor', 'ingredientList']),
  },
  methods: {
    ...mapActions(['userDisfavor','fetchIngredientList']),
  },
  created() {
    // tmp 데이터
    this.$store.commit('SET_INGREDIENTLIST', [
      { id: 1, name: '재료1' },
      { id: 2, name: '재료2' },
      { id: 3, name: '재료3' },
    ]);
    this.$store.commit('SET_USERDISFAVOR', [
      { id: 1, name: '비선호재료1' },
      { id: 2, name: '비선호재료2' },
    ]);
  },

};
</script>

<style>
#hashTagkeyword {
    display: flex;
    justify-content: center;
}
#hash {
    margin: 1rem;
    background-color: #FD7E14;
    color: white;
    width: 5rem;
    padding: 0.6rem;
    border-radius: .5rem;
    text-decoration-line: none;
}
.input-group {
    width: 60%;
    height: 2.5rem;
    margin: auto;
    margin-top: 3.5rem;
}

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

@media screen and (max-width: 992px){
    .input-group {
        width: 80%;
        margin: auto;
        margin-top: 3.5rem;
    }
}
</style>
