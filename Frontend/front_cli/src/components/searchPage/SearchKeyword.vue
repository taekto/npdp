<template>
    <!-- 키워드가 들어간 검색 페이지 -->
    <div>
        <!-- 검색창 컴포넌트 -->
        <div class="searchWindow">
            <form @submit.prevent="recipeSpecific(searchKeyword)">
                <!-- 검색창 -->
                <div class="input-group">
                    <input id="searchForm" class="form-control" type="text" v-model.trim="searchKeyword">
                    <input id="submitButton" type="submit" value="검색">
                </div>

                <!-- 해시태그 -->
                <div id="hashTagkeyword">
                    <!-- <router-link id="hash" :to="{
                        name: 'searchKeyword',
                        params: {
                            keyword: tag
                        }
                    }" v-for="(tag, index) in hashTag" :key="index">
                        # {{ tag }}
                    </router-link> -->
                    <div @click="recipeSpecific(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
                        # {{ tag }}
                    </div>
                </div>
            </form>
        </div>

        <!-- 상세 검색 컴포넌트 -->
        <DetailSearch class="detailSearch" />

        <!-- 검색 결과 컴포넌트 -->
        <SearchResult />
    </div>
</template>

<script>
import {mapActions} from 'vuex'

import DetailSearch from './detailSearch.vue'
import SearchResult from './searchResult.vue'


export default {
    components: {
        DetailSearch,
        SearchResult,
    },
    // 키워드는 vuex에서 관리할 것
    data() {
        return {
            keyWord : "",
            searchKeyword : "",
            hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
            keyword: this.$route.params.keyword,
        }
    },
    methods: {
        // 키워드를 통해 검색하도록 하는 함수
        ...mapActions(['recipeSpecific']),

        // 재료데이터 다 받아옴
        // find 키워드 동일한거 추려서
        // 해당 레시피 id를 들고 axios요청하면 그 데이터 상세조회
        goToSearchwithKeyword() {
            // 키워드를 통해 검색하는 과정
            this.keyWord = ""
            const tempKeyword = this.searchKeyword
            tempKeyword.toLowerCase()
            console.log(tempKeyword)
            if(tempKeyword == "" || tempKeyword == "null") {
                this.$router.push({
                    name: 'search',
                })
            }
            else {
                this.$router.push({
                name: "searchKeyword",
                params: { keyword : tempKeyword }
                })
            }

            // 검색창 초기화
            this.keyWord = this.searchKeyword
            this.searchKeyword = ""
        }
    }
    
}
</script>