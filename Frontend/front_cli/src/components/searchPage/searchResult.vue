<template>
    <div>
        <div class="list">
            <div v-for="(recipe_item, index) in displayedItems" :key="index">
                <div class="recommendCard" @click="goToDetailRecipe(recipe_item.recipeId)">
                    <img :src="recipe_item.imgBig" alt="">
                    <p class="recipeName">{{recipe_item.name}}</p>
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
    </div>
    
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'SearchResult',
    props: {
        classification: String,
        keyWord: [String, null],
    },
    data() {
        return {
            itemsPerPage: 15,
            page: 1,
            classificationData : '전체',
            keyWordData: null,
        }
    },
    computed: {
        ...mapGetters(['recipe','recipeSpecific']),
        totalPages() {
            return Math.ceil(this.recipeSpecific.length / this.itemsPerPage)
        },
        displayedItems() {
            const startIndex = (this.page - 1) * this.itemsPerPage
            const endIndex = startIndex + this.itemsPerPage
            return this.recipeSpecific.slice(startIndex, endIndex)
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
        ...mapActions(['recipeSpecificSearch', 'querySearch']),
        goToPage(pageNumber) {
            if (pageNumber >= 1 && pageNumber <= this.totalPages) {
                this.page = pageNumber;
            }
        },

        // 상세 레시피로 보내주는 함수
        goToDetailRecipe(recipeId) {      
          this.$router.push({name: "recipe",  
              params: { 
                  recipeId: recipeId
              },
            })
          }
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

    created(){
      this.recipeSpecificSearch(this.$route.params.keyword);
    },
    watch: {
      '$route.params.keyword': function(newKeyword) {
        let data = {
            searchWord : newKeyword,
            classification : this.classificationData,
            keyWord : this.keyWordData
        }
      this.querySearch(data);
      
    },
    recipeSpecific: {
      handler() {
        // recipeSpecific이 변경될 때 실행되는 로직);
        // 여기에 원하는 동작을 추가할 수 있습니다.
        this.page = 1
      },
      immediate: true, // 컴포넌트가 생성될 때 즉시 실행
    },
    classification(newValue) {
        this.classificationData = newValue
    },
    keyWord(newValue) {
        this.keyWordData = newValue
    }
    },
  };
</script>

<style scoped>
/* 검색 결과 인피니티 스크롤 */
.list {
    /* height: calc(100vh - 70px); */
    overflow: auto;
    display: flex;
    flex-wrap: wrap;
    width: 75%;
    margin: auto;
    margin-bottom: 5rem;
    justify-content: center;
}

/* .list::-webkit-scrollbar {
    display: none;
} */

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
    margin: 3rem 2rem;
    font-size: 2rem;
    font-family: 'LINESeedKR-Bd';
}

.recipeButton {
    margin-top: 1rem;
    background-color: #FD7E14;
    color: white;
    border: none;
    border-radius: .5rem;
    padding: .5rem;
    font-size: 1rem;
}

@media screen and (max-width: 992px) {
  .recommendCard {
    /* border-radius: .5rem;
    box-shadow: 2px 2px 2px 2px;
    height: 20rem;
    width: 15rem;
    cursor: pointer;
    margin: 1rem; */
    border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    box-shadow: 0 4px 4px -4px black;
    /* border: 1px solid #857f7b; */
    height: 23rem;
    width: 18rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    transition: 0.3s;
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

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 4rem;
}
.pagination button {
  font-family: 'LINESeedKR-Rg';
  margin: 0 5px;
  border-radius: .2rem;
  border: 1px solid #FD7E14;
  background-color: #fff;
  padding: .25rem .7rem;
}
</style>