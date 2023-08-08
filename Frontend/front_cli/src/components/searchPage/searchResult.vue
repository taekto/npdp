<template>
    <!-- 검색 결과가 띄워질 컴포넌트 -->
    <ul
        ref="notification-list"
        class="list"
        @scroll="handleNotificationListScroll"
    >
        <!-- 키워드와 관련된 레시피들을 추천 -->
        <!-- 현재는 임시 값으로 이미지와 이름만 사용 -->
        <div
        v-for="recipe_item in recipeSpecific"
        :key="recipe_item.recipe_id"
        >
            <!-- 레시피 카드로 표현 -->
            <div class="recommendCard" @click="goToDetailRecipe(recipe_item.recipeId)">
                <img :src="recipe_item.imgBig" alt="">
                <!-- <p>Recipe Name</p> -->
                <!-- <img :src="recipe_item.img_small" alt=""> -->
                <p class="recipeName">{{recipe_item.name}}</p>
                
                <button class="recipeButton">View More</button>
            </div>
        </div>
    </ul>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'SearchResult',
    computed: {
        ...mapGetters(['recipe', 'recipeSpecific'])
    },

    methods: {
        ...mapActions(['detailRecipe']),
        // 상세 레시피로 보내주는 함수
        // 데이터 연결 후 변경 예정
        goToDetailRecipe(recipe_id) {
            this.detailRecipe(recipe_id)
            setTimeout(() => {
                this.$router.push({name: "recipe",  
                    params: { 
                        recipe_id: recipe_id
                    },
                })
            }, 200) 
        },

    // 내려오면 api 호출하여 아래에 더 추가, total값 최대이면 호출 안함
    handleLoadMore() {
      if (50 < this.total) {
          const params = {
          limit: this.params.limit,
          page: this.params.page + 1
          };
          this.$store.commit(
          "notification/SET_PARAMS",
          this.filterValue ? { ...params, type: this.filterValue } : params
          );
          this.dispatchGetNotifications(false);
      }
    },

    }
  };
</script>

<style scoped>
/* 검색 결과 인피니티 스크롤 */
.list {
    height: calc(100vh - 70px);
    overflow: auto;
    display: flex;
    flex-wrap: wrap;
    width: 75%;
    margin: auto;
    margin-bottom: 5rem;
    justify-content: center;
}

/* 검색 결과 레시피 카드 */
.recommendCard {
    border-radius: .5rem;
    box-shadow: 2px 2px 2px 2px;
    height: 25rem;
    width: 20rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
}

.recommendCard p {
    margin-top: 2rem;
    font-size: 2.5rem;
}

img {
    width: 90%;
    height: 50%;
    margin-top: 1rem;
}

.recipeName {
    white-space: nowrap;
    overflow: hidden;
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
    height: 20rem;
    width: 15rem;
    cursor: pointer;
    margin: 1rem;
    }

    .recommendCard p {
    margin-top: 2rem;
    font-size: 1.5rem;
    }
    .list {
        height: calc(100vh - 70px);
        overflow: auto;
        display: flex;
        flex-wrap: wrap;
        width: 80%;
        margin: auto;
        margin-bottom: 5rem;
        justify-content: center;
    }
}
</style>