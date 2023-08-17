<template>
  <!-- 상단 광고 슬라이드 -->
  <div class="main_image_container">
    <carousel :items-to-show="1" :autoplay= "3500"
  :transition = "1000" :wrap-around="true">
    <slide v-for="slide in 3" :key="slide">
      <SlideCardImageVue :slide = slide />
      <div class="explanation">
      <!-- <div class="explanationBox"> -->
        <div class="main_carousel_text_box">
          <h3>1114+</h3>
          <p>Recipes</p>
        </div>
        <div class="main_carousel_text_box">
          <h3>2997+</h3>
          <p>Ingredients</p>
        </div>
      <!-- </div>  -->
    </div>
    </slide>
    <!-- 슬라이드 이동 버튼 -->
    <template #addons>
      <navigation />
      <pagination />
    </template>
  </carousel>
  </div>
  
</template>

<script setup>
  import SlideCardImageVue from './SlidePage/SlideCardImage.vue'
</script>

<script>
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'

export default {
    name: "ImageSlide",
    components: {
      Carousel,
      Slide,
      Pagination,
      Navigation
    }
}
</script>

<style>
.carousel__pagination {
  position: absolute;
  display: flex;
  align-content: center;
  list-style: none;
  line-height: 0;
  top: 95%;
  left: 45%;
}

.main_image_container {
  position: relative;
}

.explanation {
  position: absolute;
  padding: 20px;
  /* background-color: #FD7E14; */
  color: white;
  z-index: 2;
  display: flex;
  justify-content: space-around;
}

.explanationBox {
  top: 50%;
  left: 50%;
  width: 35%;
  /* display: flex;
  justify-content: space-around; */
  margin: auto;
}

.main_carousel_text_box {
  margin: 0 50%;
}

</style>