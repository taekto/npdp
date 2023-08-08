<template>
  <!-- 상단 광고 슬라이드 이미지(임시) -->
  <!-- <img v-if="slide == 1" src="@/assets/mainImage01.jpg" alt="">
  <img v-else-if="slide == 2" src="@/assets/mainImage02.jpg" alt="">
  <img v-else-if="slide == 3" src="@/assets/mainImage04.jpg" alt=""> -->
  <div v-if="slide == 1" class="carousel_one"></div>
  <div v-else-if="slide == 2" class="carousel_two"></div>
  <div v-else-if="slide == 3" class="carousel_three"></div>
</template>

<script>
export default {
  name: "SlideCardImage",
  props: {
    slide: Number
  }
}
</script>

<style scoped>
/* 상단 이미지 */
img {
  width: 100%;
  height: 45rem;
}

.carousel_one {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 70vh;
    background: url("@/assets/mainImage01.jpg") center;
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}

.carousel_two {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 70vh;
    background: url("@/assets/mainImage02.jpg") center;
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}

.carousel_three {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 70vh;
    background: url("@/assets/mainImage03.jpg") center;
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}

.carousel_one::after, .carousel_two::after, .carousel_three::after{
    content: "";
    width: 100%;
    height: 100%;
    background-color: black;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: -1;
    opacity: 0.3;
}

</style>