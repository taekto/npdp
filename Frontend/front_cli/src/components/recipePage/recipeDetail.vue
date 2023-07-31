<template>
  <!-- 레시피 상세 페이지 -->
  <div class="recipeDetail">
    <!-- 레시피 이름 -->
    <div class="recipeName">
      <h2 class="recipeTitle">레시피이름 : {{recipe.name}}</h2>
      <button v-if="liked" class="likeButton" @click="changeLike">Like</button>
      <button v-else class="dislikeButton" @click="changeLike">Dislike</button>
    </div>

    <div class="recipeInfomation">
      <!-- 레시피 이미지 -->
      <img class="recipeImage" src='@/assets/123.jpg'>
      
      <!-- 레시피 정보 -->
      <RecipeInfomation :recipe=recipe />
    </div>

    <!-- 레시피 순서 -->
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
    // 추후 데이터 기반 props 필요
    // props: {
    //   recipe_id : Number,
    // },

    // 현재는 더미 데이터를 만들어서 확인
    data() {
      return {
        liked : false,
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
    },
    methods: {
      changeLike() {
        this.liked = this.liked ? false : true;
      }
    }
}
</script>

<style scoped>
/* 레시피 상세 */
.recipeDetail {
    width: 75%;
    margin-top: 2rem;
    margin: auto;
}

/* 레시피 이름 */
.recipeName {
    margin-top: 2rem;
    margin-left: 2rem;
    margin-bottom: 2rem;
    text-align: start;
    display: flex;
}

.recipeTitle {
    font-weight: bold;
    margin-right: 2rem;
}

/* 좋아요 버튼 구분 */
.likeButton {
    border-radius: .5rem;
    background-color: #FD7E14;
    border: none;
    color: white;
    padding-left: 1rem;
    padding-right: 1rem;
}

.dislikeButton {
    border-radius: .5rem;
    color: #FD7E14;
    border: solid #FD7E14;
    background-color: white;
    padding-left: 1rem;
    padding-right: 1rem;
}

/* 레시피 이미지 & 레시피 정보 */
.recipeInfomation {
    display: flex;
    justify-content: space-around;
}

/* 레시피 이미지 */
.recipeImage {
    width: 45%;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

/* 레시피 순서 */
.recipeOrder {
    margin-top: 3rem;
}
/* 레시피 순서 타이틀 */
.orderTitle {
    text-align: start;
    margin-left: 3rem;
    margin-top: 5rem;
    margin-bottom: 3rem;
    font-weight: bold;
}

/* 레시피 순서 각 컴포넌트 */
.orderLine {
    display: flex;
    justify-content: space-around;
    margin-bottom: 3rem;
}

/* 순서 설명 */
.orderExplain {
    width: 45%;
    margin: 2rem;
}

/* 순서 관련 이미지 */
.orderImage{
    width: 35%;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

</style>