<template>
    <!-- 키워드가 들어간 검색 페이지 -->
    <div>
      
        <!-- 검색창 컴포넌트 -->
        <div class="searchWindow">
          <h1>{{selectCategory}}</h1>
          <form @submit.prevent="goToSearchwithKeyword(searchKeyword)">
            <!-- 검색창 -->
            <div class="input-group">
              <input id="searchForm" class="form-control" type="text" v-model.trim="searchKeyword">
              <input id="submitButton" type="submit" value="검색">
            </div>

            <!-- 해시태그 -->
            <div id="hashTagkeyword">
              <div @click="goToSearchwithKeyword(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
                {{ tag }}
              </div>
            </div>
          </form>
        </div>

        <!-- 상세 검색 컴포넌트 -->
        <!-- <DetailSearch class="detailSearch" @changeClassification="changeClassification" @changeCategory="changeCategory" /> -->
        <DetailSearch class="detailSearch" @changeClassification="changeClassification" @changeCategory="changeCategory" />


        <!-- 검색 결과 컴포넌트 -->
        <SearchResult :classification='classification' :keyWord='keyWord'/>
    </div>
</template>

<script>

import DetailSearch from './detailSearch.vue'
import SearchResult from './searchResult.vue'
import {mapActions, mapGetters} from 'vuex'

export default {
    components: {
        DetailSearch,
        SearchResult,
    },
    // 키워드는 vuex에서 관리할 것
    data() {
        return {
          searchKeyword : "",
          hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
          keyword: this.$route.params.keyword,
          classification: "전체",
          keyWord : null,
        }
    },
    computed: {
      ...mapGetters(['selectCategory', 'selectClassification'])
    },
    methods: {
      ...mapActions(['querySearch']),
      async changeClassification(data) {
        await this.$nextTick()
        this.classification = data
      },
      async changeCategory(data) {
        await this.$nextTick()
        this.keyWord = data
      },

      goToSearchwithKeyword(word) {
        const tempKeyword = word.trim().toLowerCase()
        const data = {      
          searchWord : tempKeyword,
          classification : this.classification,
          keyWord : this.keyWord
        }
        this.querySearch(data)
        this.$router.push({
          name: "searchKeyword",
          params: { keyword: tempKeyword }
        })

          this.searchKeyword = "";
        }
    },
}
</script>

<style scoped>
/* 해시태그 */
#hashTagkeyword {
    display: flex;
    justify-content: center;
}

#hash {
    font-family: 'GangwonEdu_OTFBoldA';
    margin: 1rem;
    background-color: #FD7E14;
    color: white;
    width: 5rem;
    padding: 0.6rem;
    border-radius: .5rem;
    text-decoration-line: none;
    cursor: pointer;
}
</style>