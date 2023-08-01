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
        v-for="(recipe_item, index) in recipe"
        :key="index"
        >
            <!-- 레시피 카드로 표현 -->
            <div class="recommendCard" @click="goToDetailRecipe">
                <img src="@/assets/123.jpg" alt="">
                <!-- <p>Recipe Name</p> -->
                <p>{{recipe_item.name}}</p>
                
                <button class="recipeButton">View More</button>
            </div>
        </div>
    </ul>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    name: 'SearchResult',
    computed: {
        ...mapGetters(['recipe'])
    },

    methods: {
        // 상세 레시피로 보내주는 함수
        // 데이터 연결 후 변경 예정
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