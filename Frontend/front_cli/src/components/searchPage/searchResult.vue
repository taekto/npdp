<template>
  <ul
    ref="notification-list"
    class="list"
    @scroll="handleNotificationListScroll"
  >
    <div
    v-for="(number, index) in 50"
    :key="index"
    >
        <div class="recommendCard" @click="goToDetailRecipe">
            <img src="@/assets/123.jpg" alt="">
            <p>Recipe Name</p>
            <button class="recipeButton">View More</button>
        </div>
    </div>
  </ul>
</template>

<script>
export default {
    name: 'SearchResult',

    methods: {
        goToDetailRecipe() {
            this.$router.push('recipe')
        },

        // 무한 스크롤 정의
        handleNotificationListScroll(e) {
        const { scrollHeight, scrollTop, clientHeight } = e.target;
        const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
        // 일정 한도 밑으로 내려오면 함수 실행
        if (isAtTheBottom) this.handleLoadMore();
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
<style>
.list {
  height: calc(100vh - 70px);
  overflow: auto;
  display: flex;
  flex-wrap: wrap;
  width: 75%;
  margin: auto;
}
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
    height: 20rem;
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