<template>
    <!-- 검색창 컴포넌트 -->
    <div>
        <form @submit.prevent="recipeSpecificSearch(content)">
            <div class="input-group">
                <!-- <input id="submitButton" type="submit" value="검색"> -->
                <i class="bi bi-search" @click="recipeSpecificSearch(content)"></i>
                <input id="searchForm" class="form-control" type="text" v-model.trim="content" placeholder="검색어를 입력해주세요.">
            </div>
            <!-- 해시태그 -->
            <div id="hashTagkeyword">
                <div @click="recipeSpecificSearch(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
                    # {{ tag }}
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            content : "",    
            keyWord : "",
            hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
        }
    },
    methods: {
        ...mapActions(['recipeSpecificSearch']),
        goToSearchwithKeyword() {
            this.keyWord = ""
            const tempKeyword = this.word.content
            tempKeyword.toLowerCase()
            console.log(tempKeyword)
            if(tempKeyword == "" || tempKeyword == "null") {
                this.$router.push({
                    name: 'search',
                })
            }
            else {
                this.$router.push({
                name: "content",
                params: { keyword : tempKeyword }
                })
            }

            
            this.keyWord = this.content
            this.content = ""
        }
    }
    
}
</script>

<style scoped>
/* 해시태그 */
#hashTagkeyword {
    display: flex;
    justify-content: center;
}

#hash {
    font-family: 'LINESeedKR-Rg';
    margin: 1rem;
    background-color: #FD7E14;
    color: #ffffff;
    width: 5rem;
    padding: 0.5rem;
    border: 1px solid #fd7e14;
    border-radius: 2rem;
    text-decoration-line: none;
}

#hash:hover {
    border: 1px solid #fd7e14;
    background-color: #ffffff;
    color: #fd7e14;
}

.bi-search {
    width: 10%;
    margin: auto;
    cursor: pointer;
}

/* 검색창 */
.input-group {
    width: 60%;
    height: 3.5rem;
    margin: auto;
    margin-top: 3.5rem;
    border: solid #FD7E14 1px;
    border-radius: .3rem;
}

#searchForm {
    border: none;
    margin-right: 1rem;
    outline: none;
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

@media screen and (max-width: 992px){
    .input-group {
        width: 80%;
        /* margin: auto; */
        /* margin-top: 3.5rem; */
    }
}

@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css");
</style>