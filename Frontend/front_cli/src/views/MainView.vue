<template>
  <div>
    <!-- 상단 광고 or 정보가 들어갈 슬라이드(캐러셀) -->
    <MainImageSlideVue class="mainImage" />
    <!-- 서비스 정보 들어갈 공간 -->
    <div class="explanation">
      <h1 class="recipe_heading">1114개의 레시피</h1>
    </div>

    <!-- 검색창 컴포넌트 -->
    <searchComponentVue />

    <!-- 하단 검색 결과를 필터링 해 줄 카테고리 버튼 컴포넌트 -->
    <CategoryButton />

    <!-- 추천 레시피 컴포넌트(유사도, 좋아요순, 최근 본 순) -->
    <memberRecommendVue />
    <likeRecommendVue />
    <recentRecommendVue />
  </div>
</template>

<script setup>
  import MainImageSlideVue from '../components/mainPage/MainImageSlide.vue'
  import searchComponentVue from '../components/mainPage/searchComponent.vue'
  import memberRecommendVue from '../components/mainPage/memberRecommend.vue'
  import likeRecommendVue from '../components/mainPage/likeRecommend.vue'
  import recentRecommendVue from '../components/mainPage/recentRecommend.vue'
  import CategoryButton from '../components/mainPage/CategoryButton.vue'
</script>

<style>
/* 상단 슬라이드 관련 CSS */
.recipe_heading {
  font-family: 'Quicksand', sans-serif;
}

.explanation {
  padding: 20px;
  background-color: #FD7E14;
  color: white;
}
.carousel_pagination {
  background-color: #FD7E14;
}
</style>