<template>
    <!-- 유저 기반 추천 레시피 카드 -->
    <div class="recommendCard" @click="goToDetailRecipe(recipe.recipeId)">
        <img :src="recipe.imgSmall">
        <p class="recipeName">{{ recipe.name }}</p>
    </div>
</template>

<script>
export default {
    name: "SlideCardUserRecommend",
    props: {
        recipe : Object
    },
    methods: {
        goToDetailRecipe(recipeItem) {
            this.$router.push({name: "recipe",  
                params: { 
                    recipeId: recipeItem,
                },
            })
        },
    }
}
</script>

<style scoped>
/* 좋아요 많은 레시피 카드 */

.recommendCard {
    border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    box-shadow: 0 0 0 1px hsla(212,7%,43%,.32);
    height: 23rem;
    width: 18rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    transition: 0.3s;
}

.recommendCard:hover{
    transform: scale(1.05);
}

.recommendCard p {
    /* font-family: 'LINESeedKR-Bd';
    margin-top: 2rem 2rem 0;
    font-size: 2.5rem; */
    margin: auto;
    font-family: 'LINESeedKR-Bd';
    margin-top: 2rem;
    font-size: 2rem;
    word-break: keep-all;
}

#recipeCount {
    font-size: 1.5rem;
    text-align: end;
    /* margin-right: 1rem; */
    position: absolute;
    left: 70%;
    top: 77.5%;
    color: red;
}

img {
    width: 100%;
    height: 50%;
    /* margin-top: .7rem; */
    border-radius: 0.1rem;
}

.recipeName {
    margin: auto;
    /* word-break: keep-all; */
    overflow: hidden;
    margin: 3rem 2rem;
    font-size: 2rem;
    font-family: 'LINESeedKR-Bd';
}

.recipeButton {
    font-family: 'LINESeedKR-Rg';
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: auto;
}

@media screen and (max-width: 992px) {
  .recommendCard {
        border-radius: .5rem;
        border: 1px solid #FD7E14;
        /* box-shadow: 2px 2px 2px 2px; */
        height: 20rem;
        width: 20rem;
        cursor: pointer;
        padding: .1rem;
        margin: .5rem;
    }

    .recommendCard_img {
        border-radius: 0.5px;
    }

    .recommendCard p {
        font-family: 'LINESeedKR-Rg';
        margin-top: 2rem;
        font-size: 1.3rem;
    }
    
}
</style>