<template>
  <!-- 좋아요 많은 레시피 슬라이드 -->
  <div class="secondLine">
    <div class="likeRecommend">
        <p class="menuTitle">좋아요 많은 레시피</p>
        <div v-if="recipe">
          <Carousel :items-to-show="3" :wrap-around="true"
          :autoplay= "3500" :transition = "1000">
              <Slide v-for="recipe_item in recipe" :key="recipe_item.recipeId">
                  <SlideCardLikeRecommend :recipe = recipe_item />
              </Slide>

              <!-- 슬라이드 이동 버튼 -->
              <template #addons>
                  <Navigation />
              </template>
          </Carousel>
        </div>
        <div v-else class="likeReco_logout">
          좋아요 많은 레시피가 없습니다.
        </div>
    </div>
  </div>
    
  
</template>

<script>
import SlideCardLikeRecommend from './SlidePage/SlideCardLikeRecommend.vue'

import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import {mapGetters} from 'vuex'
import {mapActions } from 'vuex';
import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'LikeRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardLikeRecommend,
  },
  computed: {
        ...mapGetters(['recipe'])
    },
  methods: {
    ...mapActions(['fetchRecipes']),
  },
  created() {
    this.fetchRecipes();
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
/* 좋아요 많은 레시피 슬라이드 */
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
    font-family: 'KimjungchulGothic-Bold';
    margin-left: 2rem;
}
.likeReco_logout {
  font-family: 'LINESeedKR-Rg';
  font-size: 1.5rem;
  text-align: center;
  color: #ababab;
  margin: 3.5rem 0;
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