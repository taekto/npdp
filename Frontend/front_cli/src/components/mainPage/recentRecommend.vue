<template>
  <!-- 최근 본 레시피 슬라이드 -->
  <div class="thirdLine">
    <div class="recentRecommend">
        <p class="menuTitle">최근 본 레시피</p>
        <div v-if="memberRecipeLatest.length !== 0">
          <Carousel :items-to-show="calculateItemsToShow" :wrap-around="calculateWrapAround"

          :autoplay= "3500" :transition = "1000">
              <Slide v-for="item in memberRecipeLatest" :key="item.recipeId">
                  <SlideCardRecentRecommned :recipe = item />
              </Slide>

              <!-- 슬라이드 이동 버튼 -->
              <template #addons>
                <Navigation />
              </template>
          </Carousel>
        </div>
        <div v-else class="recentReco_logout">
          최근 조회한 레시피가 없습니다.
        </div>
    </div>
  </div>
    
</template>

<script>
import SlideCardRecentRecommned from './SlidePage/SlideCardRecentRecommend.vue'

import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import {mapGetters, mapActions} from 'vuex'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'RecentRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardRecentRecommned,
  },
  computed: {
    ...mapGetters(['memberRecipeLatest']),
    calculateItemsToShow() {
      if (this.memberRecipeLatest.length <= 3) {
        return this.memberRecipeLatest.length;
      } else {
        return 3; // 최대 3개까지만 보여주기
      }
    },
    calculateWrapAround() {
      return this.memberRecipeLatest.length > 3;
    },
  },

  methods:{
    ...mapActions(['latestRecipe'])
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
  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.latestRecipe({type:'get', memberId: this.memberId})
  },
})
</script>

<style scoped>
/* 최근 본 레시피 슬라이드 */
.thirdLine {
  padding-top: 1rem;
  padding-bottom: 3rem;
}

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
  font-family: 'KimjungchulGothic-Bold';
    margin-left: 2rem;
}

.recentReco_logout {
  font-family: 'LINESeedKR-Rg';
  font-size: 1.5rem;
  text-align: center;
  color: #ababab;
  margin: 3.5rem 0;
}

@media screen and (max-width: 992px) {
  .recentRecommend{
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