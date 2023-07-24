<template>
  <div>
    <MainImageSlideVue class="mainImage" />
    <div class="explanation">
      <h1>number</h1>
    </div>
    <searchComponentVue />
    <CategoryButton />
    <userRecommendVue />
    <likeRecommendVue />
    <recentRecommendVue />
  </div>
</template>

<script setup>
  import MainImageSlideVue from '../components/mainPage/MainImageSlide.vue'
  import searchComponentVue from '../components/mainPage/searchComponent.vue'
  import userRecommendVue from '../components/mainPage/userRecommend.vue'
  import likeRecommendVue from '../components/mainPage/likeRecommend.vue'
  import recentRecommendVue from '../components/mainPage/recentRecommend.vue'
  import CategoryButton from '../components/mainPage/CategoryButton.vue'
</script>

<style scoped>
.mainImage {
  background-color: #9d9d9d;
}

.explanation {
  padding-top: 1rem;
  padding-bottom: 1rem;
  background-color: #FD7E14;
  color: white;
}
</style>