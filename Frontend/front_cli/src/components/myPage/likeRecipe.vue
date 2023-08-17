<template>
  <!-- 마이페이지 좋아요한 레시피 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 좋아요한 레시피 컴포넌트 -->
    <div id="myPageView" v-if="memberRecipeLike.length > 0">
      <!-- <p class="menuTitle">좋아요</p> -->

      <div class="list">
          <div v-for="(recipe_item, index) in displayedItems" :key="index">
              <div class="recommendCard" @click="goToDetailRecipe(recipe_item.recipeId)">
                  <img :src="recipe_item.imgBig" alt="">
                  <p class="recipeName">{{recipe_item.name}}</p>
                  <div class="buttonGroup">
                    <button class="deleteButton" @click.stop="handleUnlike(recipe_item.recipeId)">취소</button>
                  </div>
              </div>
          </div>
      </div>

      <div class="pagination">
            <button @click="goToPage(1)" :disabled="page === 1">처음</button>
            <button @click="goToPage(page - 1)" :disabled="page === 1">이전</button>
            <button v-for="pageNumber in displayedPageNumbers" :key="pageNumber" @click="goToPage(pageNumber)" :disabled="pageNumber === page">
                {{ pageNumber }}
            </button>
            <button @click="goToPage(page + 1)" :disabled="page === totalPages">다음</button>
            <button @click="goToPage(totalPages)" :disabled="page === totalPages">끝</button>
        </div>
      
      <!-- <div  class="likeRecipes" v-for="item in memberRecipeLike" :key="item.recipeId">
        <div class="recommendCard" @click.prevent="goToDetailRecipe(item)">
          <img :src='item.imgBig'>
          <p class="recipeName">{{ item.name }}</p>
          <div class="buttonGroup">
            <button class="deleteButton" @click.stop="handleUnlike(item.recipeId)">좋아요 취소</button>
          </div>
        </div>
      </div> -->
    </div>
    <div v-else id="myPageView">
      <!-- <p class="menuTitle">좋아요</p> -->
      <p class="memberReco_logout">좋아하는 레시피를 추가해보세요!</p>
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
      itemsPerPage: 6,
      page: 1,
    }
  },
  components: {
    CategoryComponent,
  },
  
  computed: {
    ...mapGetters(['memberRecipeLike']),
    totalPages() {
        return Math.ceil(this.memberRecipeLike.length / this.itemsPerPage)
    },
    displayedItems() {
        const startIndex = (this.page - 1) * this.itemsPerPage
        const endIndex = startIndex + this.itemsPerPage
        return this.memberRecipeLike.slice(startIndex, endIndex)
    },
    displayedPageNumbers() {
        const currentPageGroup = Math.ceil(this.page / 5); // Calculate current group based on current page
        const startPage = (currentPageGroup - 1) * 5 + 1; // Calculate starting page number of the group
        const endPage = Math.min(startPage + 4, this.totalPages); // Calculate ending page number of the group (up to the total pages)

        const pageNumbers = [];
        for (let i = startPage; i <= endPage; i++) {
            pageNumbers.push(i);
        }

        return pageNumbers;
    },
  },
  methods: {
    ...mapActions(['memberLikeRecipe','fetchLike']),
    goToPage(pageNumber) {
        if (pageNumber >= 1 && pageNumber <= this.totalPages) {
            this.page = pageNumber;
        }
    },
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
.list {
    /* height: calc(100vh - 70px); */
    overflow: auto;
    display: flex;
    flex-wrap: wrap;
    /* width: 90%; */
    margin: auto;
    margin-bottom: 5rem;
    justify-content: center;
}

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 4rem;
}
.pagination button {
  font-family: 'LINESeedKR-Rg';
  color: #FD7E14;
  margin: 0 5px;
  border-radius: .2rem;
  border: 1px solid #FD7E14;
  background-color: #fff;
  padding: .25rem .7rem;
}

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
    margin-top: 5rem;
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
  font-family: 'LINESeedKR-Rg';
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
    margin: .5rem;
}

.memberReco_logout {
  font-family: 'LINESeedKR-Rg';
  margin: auto;
  font-size: 1.5rem;
  text-align: center;
  vertical-align: middle;
  color: #ababab;
  margin: 10rem 0;
}

/* @import "../assets/CSS/myPage.css" */
</style>