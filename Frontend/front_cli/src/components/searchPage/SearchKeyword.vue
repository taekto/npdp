<template>
    <!-- 키워드가 들어간 검색 페이지 -->
    <div>
        <!-- 검색창 컴포넌트 -->
        <div class="searchWindow">
            <form @submit.prevent="recipeSpecificSearch(searchKeyword)">
                <!-- 검색창 -->
                <div class="input-group">
                    <input id="searchForm" class="form-control" type="text" v-model.trim="searchKeyword">
                    <input id="submitButton" type="submit" value="검색">
                </div>

                <!-- 해시태그 -->
                <div id="hashTagkeyword">
                    <div @click="recipeSpecificSearch(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
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
import {mapActions, mapGetters} from 'vuex'

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
            recipeAmount : 0,
        }
    },
    computed: {
        ...mapGetters(['recipeSpecific'])
    },
    mounted() {
        this.recipeAmount = this.recipeSpecific.length
    },
    methods: {
        // 키워드를 통해 검색하도록 하는 함수
        ...mapActions(['recipeSpecificSearch']),

    }
    
}
</script>