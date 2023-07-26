<template>
  <carousel :items-to-show="1" :autoplay= "3500"
  :transition = "1000" :wrap-around="true">
    <slide v-for="slide in 3" :key="slide">
      <SlideCardImageVue :slide = slide />
    </slide>

    <template #addons>
      <navigation />
      <pagination />
    </template>
  </carousel>
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

</style>