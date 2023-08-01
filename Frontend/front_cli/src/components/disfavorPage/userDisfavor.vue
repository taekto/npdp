<template>
  <div>
    <h3>비선호 재료 검색</h3>
    <div class="ingredient_search">
      <input v-model="searchKeyword" class="disfavor_search_form" type="text" @input="searchDisfavorIngredients">
      <button @click="searchDisfavorIngredients">추가</button>
    </div>
    <ul>
      <li v-for="ingredient in searchResults" :key="ingredient.id">
        {{ ingredient.name }}
        <button @click="addToDisfavorList(ingredient)">추가</button>
      </li>
    </ul>
    <h3>비선호 재료 리스트</h3>
      <div class="user_disfavor_list">
        <span v-for="ingredient in userDisfavorList" :key="ingredient.id">
          {{ ingredient.name }} |
        </span>
      </div>
    <button @click="saveUserDisfavorList">저장</button>
  </div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  data() {
    return {
      searchKeyword: '',
      searchResults: [],
    };
  },
  computed: {
    ...mapGetters(['ingredientList', 'userDisfavorList']),
  },
  methods: {
    ...mapActions(['userDisfavorList', 'fetchIngredientList']),

    searchDisfavorIngredients() {
      if (!this.searchKeyword) {
        this.searchResults = [];
        return;
      }

      // 사용자가 입력한 검색어와 유사한 재료를 찾습니다.
      this.searchResults = this.ingredientList.filter((ingredient) =>
        ingredient.name.includes(this.searchKeyword)
      ).slice(0, 5);
    },
    userDisfavorList(ingredient) {
      if (!this.userDisfavorList.some((item) => item.id === ingredient.id)) {
        this.userDisfavorList.push(ingredient);
      }
    },
    saveUserDisfavorList() {
      // 저장기능 구현해야함
    },
  },
  created() {
    this.fetchIngredientList();
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
