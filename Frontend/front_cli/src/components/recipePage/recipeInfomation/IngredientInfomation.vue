<template>
  <!-- 레시피 재료 정보 박스 -->
  <div>
    <!-- 각 컬럼이 무엇을 뜻하는 지 설명 -->
    <div class="ingredientLine menu">
      <div class="ingredientName">
        <p>재료</p>
      </div>
    </div>
    

    <!-- 재료 정보 -->
    <div class="ingredientContainer">
      <div class="ingredientLine" v-for="(ingredient, index) in recipeDetail.recipeIngredients" :key="index">
          <div class="ingredientName">
            <p>{{ingredient.kor}}</p>
          </div>
          <div class="ingredientAmount">
            <p>{{(ingredient.amount * serving).toFixed(1)}}</p>
            <p>{{ingredient.unit}}</p>
          </div>
      </div>
    </div>

    <div class="ingredientLine menu">
      <div class="ingredientName">
        <p>양념</p>
      </div>
    </div>

    <div class="ingredientContainer">
      <div class="ingredientLine" v-for="(ingredient, index) in recipeDetail.recipeSeasonings" :key="index">
          <div class="ingredientName">
            <p>{{ingredient.kor}}</p>
          </div>
          <div class="ingredientAmount">
            <p>{{(ingredient.amount * serving).toFixed(1)}}</p>
            <p>{{ingredient.unit}}</p>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    name: 'IngredientInfomation',
    props: {
      // serving : Number
    },
    data() {
      return {
        ingredientData : [],
      }
    },
    computed: {
      ...mapGetters(['recipeDetail'])
    },
    // watch: {
    //   recipeDetail() {
    //     this.setIngredient()
    //   }
    // },
}
</script>

<style scoped>
.ingredientContainer {
  display: flex;
  flex-wrap: wrap; /* 자식 요소가 한 줄에 모두 나타나지 않을 경우 줄바꿈 */
  justify-content: center;
  /* align-items: center; */
  /* width: ; */
}

.ingredientLine {
  margin-top: 2rem;
  font-family: 'LINESeedKR-Bd';
  width: 15%;
  display: flex;
  margin-left: 2rem;
  margin-right: 2rem;
  /* margin-bottom: 1rem; */
  /* border: solid rgb(160, 160, 160);
  border-radius: .5rem; */
  /* display: flex; */
}

.ingredientAmount {
  margin-left: 2rem;
}
</style>
