// <template>
//     <!-- 키워드 없는 레시피 검색 페이지 -->
//     <div>
//         <!-- 검색창 컴포넌트 -->
//         <div class="searchWindow">
//             <form @submit.prevent=" goToSearchwithKeyword(searchKeyword)">
//                 <!-- 검색창 -->
//                 <div class="input-group">
//                     <input id="searchForm" class="form-control" type="text" v-model.trim="searchKeyword">
//                     <input id="submitButton" type="submit" value="검색">
//                 </div>
//                 <div id="hashTagkeyword">
//                     <div @click="goToSearchwithKeyword(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
//                         # {{ tag }}
//                     </div>
//                 </div>
//             </form>
//         </div>
        

//         <!-- 상세 검색 컴포넌트 -->
//         <DetailSearch class="detailSearch" />

//         <!-- 검색 결과 컴포넌트 -->
//         <SearchResult />
//     </div>
// </template>

// <script>

// import DetailSearch from './detailSearch.vue'
// import SearchResult from './searchResult.vue'

// export default {
//     components: {
//         DetailSearch,
//         SearchResult,
//     },

//     // 키워드는 vuex에서 관리할 것
//     data() {
//         return {
//             keyWord : "",
//             searchKeyword : "",
//             hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
//         }
//     },
//     methods: {
//         goToSearchwithKeyword(word) {      
//             this.keyWord = ""
//             const tempKeyword = word
//             tempKeyword.toLowerCase()
//             console.log('asdasdasdasdaadasdasd',tempKeyword)
//             if(tempKeyword == "" || tempKeyword == "null") {
//                 this.$router.push({
//                     name: 'search',
//                 })
//             }
//             else {
//                 this.$router.push({
//                 name: "searchKeyword",
//                 params: { keyword : tempKeyword }
//                 })
//             }

//             // 검색창 초기화
//             this.keyWord = this.searchKeyword
//             this.searchKeyword = ""
//         }
//     }
    
// }
// </script>

// <style>
// /* 해시태그 */
// #hashTagkeyword {
//     display: flex;
//     justify-content: center;
// }
// #hash {
//     margin: 1rem;
//     background-color: #FD7E14;
//     color: white;
//     width: 5rem;
//     padding: 0.6rem;
//     border-radius: .5rem;
//     text-decoration-line: none;
//     cursor: pointer;
// }
// </style>

// <style scoped>
// </style>