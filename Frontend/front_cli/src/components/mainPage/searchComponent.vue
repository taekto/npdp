<template>
    <!-- 검색창 컴포넌트 -->
    <div>
        <form @submit.prevent="recipeSpecificSearch(content)">
            <div class="input-group">
                <input id="searchForm" class="form-control" type="text" v-model.trim="content">
                <input id="submitButton" type="submit" value="검색">
            </div>
            <!-- 해시태그 -->
            <!-- <div id="hashTagkeyword">
                <router-link id="hash" :to="{
                    name: 'content',
                    params: {
                        keyword: tag
                    }
                }" v-for="(tag, index) in hashTag" :key="index">
                    # {{ tag }}
                </router-link>
            </div> -->
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
    margin: 1rem;
    background-color: #FD7E14;
    color: white;
    width: 5rem;
    padding: 0.6rem;
    border-radius: .5rem;
    text-decoration-line: none;
}

/* 검색창 */
.input-group {
    width: 60%;
    height: 2.5rem;
    margin: auto;
    margin-top: 3.5rem;
    
}

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
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
        margin: auto;
        margin-top: 3.5rem;
    }
}

</style>