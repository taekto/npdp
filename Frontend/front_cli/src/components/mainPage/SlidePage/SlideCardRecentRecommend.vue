<template>
    <!-- 최근 본 레시피 카드 -->
    <div class="recommendCard" @click="goToDetailRecipe(recipe)">
        <img src='@/assets/123.jpg'>
        <p>{{ recipe.name }}</p>
        <button class="recipeButton">View More</button>
    </div>
</template>

<script>
export default {
    name: "SlideCardRecentRecommend",
    props: {
        recipe : Object
    },
    methods: {
        goToDetailRecipe(recipeItem) {
            this.$router.push({name: "recipe",  
                params: { 
                    recipe_id: recipeItem.recipe_id,
                },
                query: {
                    recipeItem: JSON.stringify(recipeItem),
                },
            })
        },
    }
}
</script>

<style scoped>
/* 최근 본 레시피 카드 */
.recommendCard {
    border-radius: .5rem;
    /* box-shadow: 2px 2px 2px 2px; */
    height: 22rem;
    width: 20rem;
    cursor: pointer;
    /* margin: 1rem; */
    border: 1px solid #454342;
    padding: .1rem;
    margin: .3rem;
}

.recommendCard p {
    /* font-family: 'LINESeedKR-Bd';
    margin-top: 2rem 2rem 0;
    font-size: 2.5rem; */
    font-family: 'LINESeedKR-Bd';
    margin-top: 2rem;
    font-size: 1.5rem;
}

img {
    width: 90%;
    margin-top: .7rem;
    border-radius: 0.5rem;
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