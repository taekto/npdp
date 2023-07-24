<template>
  <div class="recommendCard">
    <img src="@/assets/123.jpg" alt="">
    <p>{{ slide }}</p>
  </div>
</template>

<script>
export default {
    name: "SlideCardLikeRecommend",
    props: {
        slide: Number
    }
}
</script>

<style scoped>
.recommendCard {
    border-radius: .5rem;
    border: solid black;
    height: 25rem;
    width: 20rem;
}

.recommendCard p {
    margin-top: 2rem;
    font-size: 2.5rem;
}

img {
    width: 90%;
    margin-top: .5rem;
}

</style>