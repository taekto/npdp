<template>
  <!-- 레시피 상세 페이지 -->
  <div class="recipeDetail">
    <!-- 레시피 이름 -->
    <div class="recipeName">
      <!-- <h2 class="recipeTitle">레시피이름 : {{recipe_data.name}}</h2> -->
      <p class="recipeTitle">{{recipeDetail.name}}</p>
      <div>
        <button v-if="liked" class="likeButton" @click="changeLike">Like</button>
        <button v-else class="dislikeButton" @click="changeLike">Dislike</button>
      </div>
    </div>

    <div class="recipeInfomation">
      <!-- 레시피 이미지 -->
      <div class="recipeImage">
        <img class="recipeImg" :src='recipeDetail.imgBig'>
      </div>
      
      
      <!-- 레시피 정보 -->
      <RecipeInfomation />
    </div>

    <!-- 레시피 순서 -->
    <div class="recipeOrder">
      <h2 class="orderTitle">레시피 순서</h2>
      <div class="orderLine" v-for="(order, index) in recipeDetail.recipeSequences" :key="index">
        <p class="orderExplain">{{order.description}}</p>
        <img class="orderImage" :src='order.img'>
      </div>
    </div>
    
  </div>
</template>

<script>
import RecipeInfomation from '../recipePage/recipeInfomation/recipeInfomation.vue'
import {mapGetters} from 'vuex'


export default {
    name: 'RecipeDetail',
    components: {
      RecipeInfomation,
    },
    
    computed: {
      recipeItem() {
        console.log(this.$route.params.recipeItem)
        const recipeItem = this.$route.query.recipeItem;
        if (recipeItem) {
          return JSON.parse(recipeItem);
        }
        return null;
      },
      ...mapGetters(['recipeDetail'])
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
        console.log(this.$route.params)
        console.log(this.recipe_data)
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
    font-family: 'LINESeedKR-Bd';
    justify-content: space-between;
}

.recipeTitle {
    /* font-weight: bold; */
    margin-right: 2rem;
    font-size: 2.5rem;
}

/* 좋아요 버튼 구분 */
.likeButton {
    border-radius: .5rem;
    background-color: #FD7E14;
    border: solid #FD7E14;
    color: white;
    padding-left: 1rem;
    padding-right: 1rem;
    height: 3rem;
    width: 6rem;
}

.dislikeButton {
    border-radius: .5rem;
    color: #FD7E14;
    border: solid #FD7E14;
    background-color: white;
    padding-left: 1rem;
    padding-right: 1rem;
    height: 3rem;
    width: 6rem;
}

/* 레시피 이미지 & 레시피 정보 */
.recipeInfomation {
    display: flex;
    justify-content: space-around;
}

/* 레시피 이미지 */
.recipeImage {
    width: 45%;
    height: 30rem;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

.recipeImg {
  width: 100%;
  height: 100%;
  border-radius: .5rem;
}


/* 레시피 순서 */
.recipeOrder {
    margin-top: 3rem;
    font-family: 'LINESeedKR-Bd';
    font-size: 1.25rem;
    word-break: keep-all;
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
    padding: 1rem;
    border: solid black;
}

/* 순서 관련 이미지 */
.orderImage{
    width: 35%;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

</style>