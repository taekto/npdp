<template>
  <!-- 마이페이지 좋아요한 레시피 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 좋아요한 레시피 컴포넌트 -->
    <div id="myPageView">
      <p class="likeTitle">좋아요</p>
        <div class="likeRecipes" v-for="item in memberRecipeLike" :key="item.recipeId">
          <div class="recommendCard" @click.prevent="goToDetailRecipe(item)">
            <img :src='item.imgBig'>
            <p class="recipeName">{{ item.name }}</p>
            <div class="buttonGroup">
              <button class="deleteButton" @click.stop="handleUnlike(item.recipeId)">좋아요 취소</button>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import {mapGetters, mapActions} from 'vuex'


export default {
  name: 'LikeRecipe',
  data () {
    return {
      memberId:null,
    }
  },
  components: {
    CategoryComponent,
  },
  
  computed: {
    ...mapGetters(['memberRecipeLike'])
  },
  methods: {
    ...mapActions(['memberLikeRecipe','fetchLike']),
    goToDetailRecipe(item) {
        this.$router.push({name: "recipe",  
            params: { 
                recipeId: item.recipeId,
            },
            query: {
                recipeItem: JSON.stringify(item),
            },
        })
    },
    handleUnlike(recipeId) {
      this.memberLikeRecipe({
        type: 'unlike',
        memberId: this.memberId,
        recipeId: recipeId,
      }).then(() => {
        // 좋아요 취소 후 데이터 다시 조회
        this.fetchLike(this.memberId);
      });
    },
  },
  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.fetchLike(this.memberId)
  },

}
</script>

<style scoped>
.likeRecipes {
    margin: auto;
    display: inline-flex;
    flex-wrap: wrap;
    margin-bottom: 5rem;
}

.likeTitle {
    text-align: start;
    margin-left: 10rem;
    font-size: 2rem;
    font-weight: bold;
}

/* 검색 결과 레시피 카드 */
.recommendCard {
    border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    /* box-shadow: 0 4px 4px -4px black; */
    box-shadow: 0 0 0 1px hsla(212,7%,43%,.32);
    /* border: 1px solid #857f7b; */
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

img {
    width: 100%;
    height: 50%;
    /* margin-top: 1rem; */
    border-radius: .1rem;
    /* box-shadow: 2px 2px .5px .5px; */
}

.recipeName {
    margin: auto;
    /* word-break: keep-all; */
    overflow: hidden;
    /* margin: 3rem 2rem; */
    height: 7rem;
    padding-top: 1.5rem;
    /* margin-bottom: 1rem; */
    font-size: 2rem;
    font-family: 'LINESeedKR-Bd';
}

.recipeButton {
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: .5rem;
}

.deleteButton {
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: .5rem;
}

/* @import "../assets/CSS/myPage.css" */
</style>