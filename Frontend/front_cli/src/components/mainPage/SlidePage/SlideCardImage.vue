<template>
  <!-- 상단 광고 슬라이드 이미지(임시) -->
  <img v-if="slide == 1" src="@/assets/mainImage01.jpg" alt="">
  <img v-else-if="slide == 2" src="@/assets/mainImage02.jpg" alt="">
  <img v-else-if="slide == 3" src="@/assets/mainImage04.jpg" alt="">
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
  height: 30rem;
}
</style>