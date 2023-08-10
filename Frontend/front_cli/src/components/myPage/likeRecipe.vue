<template>
  <!-- 마이페이지 좋아요한 레시피 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 좋아요한 레시피 컴포넌트 -->
    <div id="myPageView">
      <p class="likeTitle">좋아요</p>
        <div class="likeRecipes row" v-for="recipe_item in recipe" :key="recipe_item.recipe_id">
          <div class="recommendCard col-4" @click="goToDetailRecipe(recipe_item)">
            <img src='@/assets/123.jpg'>
            <p>{{ recipe_item.name }}</p>
            <div class="buttonGroup">
              <button class="recipeButton">View More</button>
              <button class="deleteButton">좋아요 삭제</button>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import {mapGetters, mapActions} from 'vuex'


export default {
  name: 'LikeRecipe',
  components: {
    CategoryComponent,
  },
  computed: {
    ...mapGetters(['recipe'])
  },
  methods: {
    ...mapActions(['memberLikeRecipe']),
    goToDetailRecipe(recipeItem) {
        this.$router.push({name: "recipe",  
            params: { 
                recipe_id: recipeItem.recipe_id,
            },
            query: {
                recipeItem: JSON.stringify(recipeItem),
            },
        })
    },
  }
}
</script>

<style scoped>
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
</style>