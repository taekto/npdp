<template>
  <div class="secondLine">
    <div class="likeRecommend">
        <p class="menuTitle">좋아요 많은 레시피</p>
        <Carousel :items-to-show="3" :wrap-around="true"
        :autoplay= "3500" :transition = "1000">
            <Slide v-for="slide in 12" :key="slide">
                <SlideCardLikeRecommend :slide = slide />
            </Slide>

            <template #addons>
                <Navigation />
            </template>
        </Carousel>
    </div>
  </div>
    
  
</template>

<script>
import SlideCardLikeRecommend from './SlidePage/SlideCardLikeRecommend.vue'

import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'LikeRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardLikeRecommend,
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
.secondLine {
  background-color: #EEEEEE;
  padding-top: 1rem;
  padding-bottom: 3rem;
}

.likeRecommend{
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

@media screen and (max-width: 992px) {
  .likeRecommend{
    width: 100%;
    margin: auto;
    margin-top: 2rem;
    margin-bottom: 2rem;
    text-align: start;
    font-size: 2rem;
    font-weight: bold;
  }
}
</style>