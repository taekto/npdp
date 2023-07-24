<template>
  <Carousel :items-to-show="2.5" :wrap-around="true"
  :autoplay= "3500" :transition = "1000">
    <Slide v-for="slide in 12" :key="slide">
      <SlideCardUserRecommend :slide = slide />
    </Slide>

    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<script>
import SlideCardUserRecommend from './SlidePage/SlideCardUserRecommend.vue'
import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'UserRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardUserRecommend
  },
  data: () => ({
    // carousel settings
    settings: {
      itemsToShow: 1,
      snapAlign: 'center',
    },
    // breakpoints are mobile first
    // any settings not specified will fallback to the carousel settings
    breakpoints: {
      // 700px and up
      700: {
        itemsToShow: 2,
        snapAlign: 'center',
      },
      // 1024 and up
      1024: {
        itemsToShow: 3,
        snapAlign: 'start',
      },
    },
  }),
})
</script>