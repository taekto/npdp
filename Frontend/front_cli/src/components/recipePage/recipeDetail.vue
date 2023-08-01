<template>
  <!-- 레시피 상세 페이지 -->
  <div class="recipeDetail">

    <!-- 레시피 이름 -->
    <div class="recipeName">
<<<<<<< HEAD
=======
      <!-- <h2 class="recipeTitle">레시피이름 : {{recipe_data.name}}</h2> -->
>>>>>>> 8a12f3913746d6ef902157d5fe95586f80ed151a
      <h2 class="recipeTitle">레시피이름 : {{recipeItem.name}}</h2>
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
    computed: {
      recipeItem() {
        console.log('-----------------')
        console.log(this.$route.params.recipeItem)
        const recipeItem = this.$route.query.recipeItem;
        if (recipeItem) {
          return JSON.parse(recipeItem);
        }
        return null;
      },
    },

    // 현재는 더미 데이터를 만들어서 확인
    
    data() {
      return {
        liked : false,
        lorem : "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
      }
    },
    methods: {
      changeLike() {
        this.liked = this.liked ? false : true;
<<<<<<< HEAD
      }
    },
    computed: {
      recipeItem() {
        console.log('-----------------')
        console.log(this.$route.params.recipeItem)
        const recipeItem = this.$route.query.recipeItem;
        if (recipeItem) {
          return JSON.parse(recipeItem);
        }
        return null;
=======
        console.log(this.$route.params)
        console.log(this.recipe_data)
>>>>>>> 8a12f3913746d6ef902157d5fe95586f80ed151a
      },
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