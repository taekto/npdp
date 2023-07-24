<template>
  <div>
    <MainImageSlideVue />
    <searchComponentVue />
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
</script>
