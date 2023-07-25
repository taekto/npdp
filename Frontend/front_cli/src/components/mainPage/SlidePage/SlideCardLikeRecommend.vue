<template>
  <div class="recommendCard" @click="goToDetailRecipe">
    <img src="@/assets/123.jpg" alt="">
    <p>{{ slide }}번 레시피</p>
    <button class="recipeButton">View More</button>
  </div>
</template>

<script>
export default {
    name: "SlideCardLikeRecommend",
    props: {
        slide: Number
    },
    methods: {
        goToDetailRecipe() {
            this.$router.push('recipe')
        }
    }
}
</script>

<style scoped>
.recommendCard {
    border-radius: .5rem;
    box-shadow: 2px 2px 2px 2px;
    height: 25rem;
    width: 20rem;
    cursor: pointer;
    margin: 1rem;
}

.recommendCard p {
    margin-top: 2rem;
    font-size: 2.5rem;
}

img {
    width: 90%;
    margin-top: 1rem;
}

.recipeButton {
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
}

@media screen and (max-width: 992px) {
  .recommendCard {
    border-radius: .5rem;
    box-shadow: 2px 2px 2px 2px;
    height: 17.5rem;
    width: 15rem;
    cursor: pointer;
    margin: 1rem;
    }
    .recommendCard p {
    margin-top: 2rem;
    font-size: 1.5rem;
}
}
</style>