<template>
  <!-- 레시피 재료 정보 박스 -->
  <div>
    <!-- 각 컬럼이 무엇을 뜻하는 지 설명 -->
    <div class="oneLine menu">
      <div class="ingredientName">
      <p>재료명</p>
      </div>
      <div class="ingredientAmount">
      <p>양</p>
      <p>/단위</p>
      </div>
    </div>

    <!-- 재료 정보 -->
    <div class="oneLine" v-for="(ingredient, index) in recipeDetail.recipeIngredients" :key="index">
      <div class="ingredientName">
      <p>{{ingredient.name}}</p>
      </div>
      <div class="ingredientAmount">
      <p>{{ingredient.amount * serving}}</p>
      <p>{{ingredient.unit}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'IngredientInfomation',
    props: {
      serving : Number
    }
}
</script>
