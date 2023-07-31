<template>
  <div class="recipeDetail">
    <div class="recipeName">
      <h2 class="recipeTitle">레시피이름 : {{recipe.name}}</h2>
    </div>
    <div class="recipeInfomation">
      <img class="recipeImage" src='@/assets/123.jpg'>
      <RecipeInfomation :recipe=recipe />
    </div>
    <div class="recipeOrder">
      <h2 class="orderTitle">레시피 순서</h2>
      <div class="orderLine" v-for="(order, index) in 5" :key="index">
        <p class="orderExplain">{{index+1}} : {{lorem}}</p>
        <img class="orderImage" src='@/assets/123.jpg'>
      </div>
    </div>
    
  </div>
</template>

<script>
import RecipeInfomation from '../recipePage/recipeInfomation/recipeInfomation.vue'

export default {
    name: 'RecipeDetail',
    components: {
      RecipeInfomation,
    },
    // props: {
    //   recipe_id : Number,
    // },
    data() {
      return {
        recipe : {
          name : "떡볶이",
          ingredients: [{ name : '감자', amount : 2, unit : '개'}, { name : '김치', amount : 1/4, unit : '포기'}, 
          {name : '설탕', amount : 50, unit : 'g'}, { name : '멸치', amount : 2, unit : '개'}, { name : '밀가루', amount : 150, unit : 'g'}],
          similarity : 75.5,
          method: { name: '조리방법', value: "Grilled"}, 
          calorie : [{ name: '중량', value: 423}, { name: '열량', value: 740}, { name: '탄수화물', value: 826},
          { name: '단백질', value: 922}, { name: '지방', value: 540}, { name: '나트륨', value: 154}]
        },
        lorem : "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
      }
    }

}
</script>

<style scoped>
.recipeDetail {
  width: 75%;
  margin-top: 2rem;
  margin: auto;
}

.recipeName {
  margin-top: 2rem;
  margin-left: 2rem;
  margin-bottom: 2rem;
  text-align: start;
  
}

.recipeTitle {
  font-weight: bold;
}

.recipeInfomation {
  display: flex;
  justify-content: space-around;
}
.recipeImage {
  width: 45%;
  border-radius: .5rem;
  border: solid rgb(205, 205, 205) 1.5px;
}

.recipeOrder {
  margin-top: 3rem;
}

.orderTitle {
  text-align: start;
  margin-left: 3rem;
  margin-top: 5rem;
  margin-bottom: 3rem;
  font-weight: bold;
}

.orderLine {
  display: flex;
  justify-content: space-around;
  margin-bottom: 3rem;
}

.orderExplain {
  width: 45%;
  margin: 2rem;
}

.orderImage{
  width: 35%;
  border-radius: .5rem;
  border: solid rgb(205, 205, 205) 1.5px;
}
</style>