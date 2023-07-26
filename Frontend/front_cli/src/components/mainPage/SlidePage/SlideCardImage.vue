<template>
  <img v-if="slide == 1" src="@/assets/123.jpg" alt="">
  <img v-else-if="slide == 2" src="@/assets/312413.jpg" alt="">
  <img v-else-if="slide == 3" src="@/assets/download.jpg" alt="">
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
img {
  width: 100%;
  height: 30rem;
}
</style>