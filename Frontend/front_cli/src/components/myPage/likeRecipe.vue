<template>
<<<<<<< HEAD
  <div>
    <div>
        <router-link to="/mypage/refrigerator">내 냉장고</router-link>
        <router-link to="/mypage/tool">내 조리도구</router-link>
        <router-link to="/mypage/like">내가 좋아요한 레시피</router-link>
        <router-link to="/mypage/edit">내 정보 수정</router-link>
    </div>
    <h1>좋아요</h1>
=======
  <!-- 마이페이지 좋아요한 레시피 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 좋아요한 레시피 컴포넌트 -->
    <div id="myPageView">
      <p class="likeTitle">좋아요</p>
        <div class="likeRecipes row" v-for="(num, index) in 30" :key="index">
          <div class="recommendCard col-4" @click="goToDetailRecipe">
            <img src='@/assets/123.jpg'>
            <p>{{ num }}번 레시피</p>
            <div class="buttonGroup">
              <button class="recipeButton">View More</button>
              <button class="deleteButton">좋아요 삭제</button>
            </div>
          </div>
        </div>
    </div>
>>>>>>> 7ce6a7470900fd4b2fcfa8cef66603bb7489b144
  </div>
</template>

<script>
<<<<<<< HEAD
export default {
    name: 'LikeRecipe'
}
</script>

<style>

=======
import CategoryComponent from './categoryComponent.vue'

export default {
    name: 'LikeRecipe',
    components: {
        CategoryComponent,
    },
    methods: {
        goToDetailRecipe() {
            this.$router.push({name: 'recipe'})
            
        }
    }
}
</script>

<style scoped>
/* 해당 파일을 외부로 적용시 오류가 생겨 해당 파일은 내부에서 CSS 적용 */

.likeRecipes {
    margin: auto;
    display: inline-flex;
    flex-wrap: wrap;
    margin-bottom: 5rem;
}

.likeTitle {
    text-align: start;
    margin-left: 10rem;
    font-size: 2rem;
    font-weight: bold;
}

.recommendCard {
    border-radius: .5rem;
    box-shadow: 2px 2px 2px 2px;
    height: 25rem;
    width: 20rem;
    cursor: pointer;
    margin: 1rem;
}

.recommendCard p {
    margin-top: 2rem;
    font-size: 2.5rem;
}

img {
    width: 90%;
    margin-top: 1rem;
}

.recipeButton {
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: .5rem;
}

.deleteButton {
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: .5rem;
}

/* @import "../assets/CSS/myPage.css" */
>>>>>>> 7ce6a7470900fd4b2fcfa8cef66603bb7489b144
</style>