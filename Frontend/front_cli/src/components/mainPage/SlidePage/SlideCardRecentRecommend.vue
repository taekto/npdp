<template>
    <!-- 최근 본 레시피 카드 -->
    <div class="recommendCard" @click="goToDetailRecipe(recipe)">
        <img :src="recipe.imgBig">
        <p>{{ recipe.name }}</p>
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
                    recipeId: recipeItem.recipeId,
                },

            })
        },
    }
}
</script>

<style scoped>
/* 최근 본 레시피 카드 */
.recommendCard {
    /* border-radius: .5rem;
    border: solid rgb(190, 190, 190) 1px;
    height: 22rem;
    width: 20rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    transition: 0.3s; */
    border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    box-shadow: 0 0 0 1px hsla(212,7%,43%,.32);
    height: 23rem;
    width: 18rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    font-size: 1rem;
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

img {
    /* width: 90%;
    margin-top: .7rem;
    border-radius: 0.5rem; */
    width: 100%;
    height: 50%;
    /* margin-top: .7rem; */
    border-radius: 0.1rem;
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
        /* border-radius: .5rem;
        border: 1px solid rgb(207, 205, 205);
        /* box-shadow: 2px 2px 2px 2px; */
        /* height: 20rem;
        width: 20rem;
        cursor: pointer;
        padding: .1rem;
        margin: .5rem; */
        border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    box-shadow: 0 0 0 1px hsla(212,7%,43%,.32);
    height: 23rem;
    width: 18rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    font-size: 1rem;
    transition: 0.3s;
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