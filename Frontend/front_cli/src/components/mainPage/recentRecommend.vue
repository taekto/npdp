<template>
    <div class="recentRecommend">
        <p class="menuTitle">최근 본 레시피</p>
        <Carousel :items-to-show="3" :wrap-around="true"
        :autoplay= "3500" :transition = "1000">
            <Slide v-for="slide in 12" :key="slide">
            <SlideCardRecentRecommned :slide = slide />
            </Slide>

            <template #addons>
            <Navigation />
            </template>
        </Carousel>
    </div>
</template>

<script>
import SlideCardRecentRecommned from './SlidePage/SlideCardRecentRecommend.vue'

import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'RecentRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardRecentRecommned,
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

<style scoped>
.recentRecommend{
    width: 75%;
    margin: auto;
    margin-top: 2rem;
    margin-bottom: 2rem;
    text-align: start;
    font-size: 2rem;
    font-weight: bold;
}

.menuTitle {
    margin-left: 2rem;
}
</style>