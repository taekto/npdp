<template>
  <div class="myPage">
    <CategoryComponent />
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
  </div>
</template>

<script>
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

</style>