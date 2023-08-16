<template>
  <!-- 유저기반 레시피 슬라이드 -->
  <div class="firstLine">
    <div class="userRecommend">
        <p class="menuTitle">유저기반 레시피</p>
        <div v-if="memberSimilarity.length !== 0">
          <Carousel :items-to-show="3" :wrap-around="true"
          :autoplay= "3500" :transition = "1000">
              <Slide v-for="item,idx in memberSimilarity" :key="idx">
                  <SlideCardUserRecommend :recipe = item />
              </Slide>

              <!-- 슬라이드 이동 버튼 -->
              <template #addons>
                  <Navigation class="arrowButton" />
              </template>
          </Carousel>
        </div>
        <div v-else class="memberReco_logout">
          재료 등록 후 이용 가능합니다
        </div>
    </div>
  </div>

  
</template>

<script>
import SlideCardUserRecommend from './SlidePage/SlideCardUserRecommend.vue'
import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

import {mapActions, mapGetters} from 'vuex' 

export default defineComponent({
  name: 'MemberRecommend',
  components: {
    Carousel,
    Slide,
    Navigation,
    SlideCardUserRecommend
  },
  computed: {
    ...mapGetters(['memberSimilarity'])
  },
  methods: {
    ...mapActions(['memberRecommend'])
  },
  data() {
    return {
      memberId: null,
      settings: {
        itemsToShow: 1,
        snapAlign: 'center',
      },
      breakpoints: {
        700: {
          itemsToShow: 2,
          snapAlign: 'center',
        },
        1024: {
          itemsToShow: 3,
          snapAlign: 'start',
        },
      },
    }
  },
  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.memberRecommend(this.memberId)
  }

})
</script>

<style scoped>


/* 유저 추천 슬라이드 */
.firstLine {
  padding-top: 1rem;
  padding-bottom: 3rem;
}

.userRecommend{
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

.memberReco_logout {
  font-family: 'LINESeedKR-Rg';
  font-size: 1.5rem;
  text-align: center;
  color: #ababab;
  margin: 3.5rem 0;
}


@media screen and (max-width: 992px) {
  .userRecommend{
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